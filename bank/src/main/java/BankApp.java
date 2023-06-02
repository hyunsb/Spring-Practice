import db.DBConnection;
import model.Account;
import model.AccountDAO;

import java.sql.Connection;
import java.util.List;

public class BankApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        AccountDAO accountDAO = new AccountDAO(connection);
//        accountDAO.createAccount(3333, "1234");
//        accountDAO.updateAccount(1111, 2000);
//        accountDAO.deleteAccount(1111);

//        Account account = accountDAO.selectAccountByAccountNumber(1111);
//        System.out.println(account);
        List<Account> accountList = accountDAO.selectAllAccount();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }
}
