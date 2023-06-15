package shop.mtcoding.bankapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bankapp.dto.account.AccountDepositReqDto;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.History;
import shop.mtcoding.bankapp.model.history.HistoryRepository;

import java.util.Objects;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, HistoryRepository historyRepository) {
        this.accountRepository = accountRepository;
        this.historyRepository = historyRepository;
    }

    // 클라이언트가 전달한 데이터는 믿을 수 없다. 가 전제로 깔려 있어야 한다.
    @Transactional
    public void accountDeposit(AccountDepositReqDto depositReqDto) {
        // 1. 입금계좌 존재 여부 체크
        Account account = accountRepository.findByNumber(depositReqDto.getDAccountNumber());
        if (Objects.isNull(account))
            throw new CustomException("입금 계좌가 존재하지 않습니다: " + depositReqDto.getDAccountNumber());

        // 2. 입금하기 (핵심 로직)
        account.deposit(depositReqDto.getAmount());
        try {
            accountRepository.updateById(account);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new CustomException("계좌 잔액 수정 에러");
        }

        // 3. 히스토리 저장
        History history = History.builder()
                .amount(depositReqDto.getAmount())
                .wAccountId(null)
                .wBalance(null)
                .dAccountId(account.getId())
                .dBalance(account.getBalance())
                .build();
        try {
            historyRepository.insert(history);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new CustomException("계좌 내역 입력 에러");
        }
    }
}
