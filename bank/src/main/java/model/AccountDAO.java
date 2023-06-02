package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private final Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    // account list
    public List<Account> selectAllAccount() {
        List<Account> accountList = new ArrayList<>();

        // 1. sql
        String query = "select * from account_tb";

        // 2. buffer
        try {
            // 3. send
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                // 4. mapping
                accountList.add(new Account(
                        resultSet.getInt("account_Number")
                        ,resultSet.getString("account_password")
                        ,resultSet.getInt("account_balance")
                        ,resultSet.getTimestamp("account_created_at")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    // account one
    public Account selectAccountByAccountNumber(int accountNumber) {
        // 1. sql
        String query = "select * from account_tb where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);

            // 3. send
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) throw new SQLException("결과가 존재하지 않습니다.");

            // 4. mapping
            return new Account(
                    resultSet.getInt("account_Number")
                    ,resultSet.getString("account_password")
                    ,resultSet.getInt("account_balance")
                    ,resultSet.getTimestamp("account_created_at"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAccount(int accountNumber, String accountPassword) {
        // 1. sql
        String query = "insert into account_tb values(?, ?, 1000, now())";

        // 2. buffer
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountPassword);

            // 3. send
            int result = preparedStatement.executeUpdate();
            System.out.println("result: "+ result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(int accountNumber, int accountBalance) {
        // 1. sql
        String query = "update account_tb set account_balance = ? where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountBalance);
            preparedStatement.setInt(2, accountNumber);

            // 3. send
            int result = preparedStatement.executeUpdate();
            System.out.println("result: "+ result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int accountNumber) {
        // 1. sql
        String query = "delete from account_tb where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountNumber);

            // 3. send
            int result = preparedStatement.executeUpdate();
            System.out.println("result: "+ result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
