package shop.mtcoding.bankapp.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.bankapp.dto.account.AccountDepositReqDto;
import shop.mtcoding.bankapp.dto.account.AccountDetailRespDto;
import shop.mtcoding.bankapp.dto.history.HistoryRespDto;
import shop.mtcoding.bankapp.handler.ex.AuthException;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.HistoryRepository;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.service.AccountService;

@Controller
public class AccountController {

    private final HttpSession session;
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;
    private final AccountService accountService;

    @Autowired
    public AccountController(HttpSession session, AccountRepository accountRepository, HistoryRepository historyRepository, AccountService accountService) {
        this.session = session;
        this.accountRepository = accountRepository;
        this.historyRepository = historyRepository;
        this.accountService = accountService;
    }

    @PostMapping("/account/deposit")
    public String deposit(AccountDepositReqDto depositReqDto) {
        // 1. 클라이언트의 request 값을 받는다. (입금 -> 입금 금액, 입금 계좌)
        // 2. 유효성 검사
        if (Objects.isNull(depositReqDto.getAmount())) {
            throw new CustomException("amount를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (depositReqDto.getAmount().longValue() <= 0) {
            throw new CustomException("입금액이 0원 이하일 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(depositReqDto.getDAccountNumber()) || depositReqDto.getDAccountNumber().isEmpty()) {
            throw new CustomException("계좌번호를 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        // 3. 입금하기 (비즈니스 로직 -> 서비스)

        // 4. response
        return "redirect:/account/depositForm";
    }

    // 컨트롤러 매개변수에는 request정보와 IoC정보를 모두 주입할 수 있다.
    @GetMapping({ "/", "/account" })
    public String main(Model model) { // (HttpServletRequest request) model에 값을 추가하면 request에 저장된다
        // 1. 인증 검사 (시큐리티, 인터셉터)
         User principal = (User) session.getAttribute("principal");
         if (Objects.isNull(principal))
             throw new AuthException("인증되지 않았습니다.");

        // 2. 레포지토리 - 조회
        List<Account> accountList = accountRepository.findByUserId(principal.getId());


        // 3. 조회된 결과를 가방(Request)에 담는다.
        model.addAttribute("accountList", accountList);
        // request.setAttribute("accountList", accountList);

        return "/account/main";
    }

    @GetMapping("/account/{id}") // pk, uk 이 아닌 모든 값들은 QueryString(?)으로 받기
    public String detail(@PathVariable int id,
                         Model model,
                         @RequestParam(name = "gubun", defaultValue = "all") String gubun) {
        // 1. 인증 검사 (시큐리티, 인터셉터)
        User principal = (User) session.getAttribute("principal");
        if (Objects.isNull(principal))
            throw new AuthException("인증되지 않았습니다.");

        AccountDetailRespDto account = accountRepository.findByIdWithUser(id);
        List<HistoryRespDto> historyList = historyRepository.findByGubun(gubun, id);

        model.addAttribute("account", account);
        model.addAttribute("historyList", historyList);

        return "account/detail";
    }

    @GetMapping("/account/saveForm")
    public String saveForm() {
        return "account/saveForm";
    }

    @GetMapping("/account/withdrawForm")
    public String withdrawForm() {
        return "account/withdrawForm";
    }

    @GetMapping("/account/depositForm")
    public String depositForm() {
        return "account/depositForm";
    }

    @GetMapping("/account/transferForm")
    public String transferForm() {
        return "account/transferForm";
    }
}
