package shop.mtcoding.bankapp.model.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.model.user.UserRepository;

import java.util.List;

@MybatisTest // (F - DS - C - S) X - (R - MyBatis) O
public class AccountRepositoryTest {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountRepositoryTest(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Test
    void findByUserId_Test() {
        int principalId = 1;

        List<Account> accountList= accountRepository.findByUserId(principalId);
        String actual = accountList.get(0).getNumber();
        String extract = "1111";

        Assertions.assertEquals(actual, extract);
    }
}
