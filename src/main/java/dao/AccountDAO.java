package dao;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Data Access Object
public class AccountDAO {
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    // account list
    public List<Account> getAccountList() {
        // 0. collection
        List<Account> accountList = new ArrayList<>();

        // 1. sql
        String query = "select * from account_tb";

        try {
            // 2. buffer
            PreparedStatement statement = connection.prepareStatement(query);

            // 3. send
            ResultSet rs = statement.executeQuery();

            // 4. get cursor to while loop -> mapping (db result -> model)
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_number"),
                        rs.getString("account_password"),
                        rs.getInt("account_balance"),
                        rs.getTimestamp("account_created_at")
                );
                // 6. collect
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    // account one row
    public Account getAccountNumber(int accountNumber) {
        // 1. sql
        String query = "select * from account_tb where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);

            // 3. send
            ResultSet rs = statement.executeQuery();

            // 4. mapping (db result -> model)
            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_number"),
                        rs.getString("account_password"),
                        rs.getInt("account_balance"),
                        rs.getTimestamp("account_created_at")
                );
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createAccount(int accountNumber, String accountPassword) {
        // 1. sql 세미 콜론 넣지 말 것, ? : 바인딩
        String query = "insert into account_tb values(?, ?, 1000, now())";

        // 2. buffer
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);
            statement.setString(2, accountPassword);

            // 3. send
            int result = statement.executeUpdate();
            // 1 : 프로토콜, 다른 숫자면 insert 제대로 된 거 아님
            System.out.println("result : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // account balance update
    public void updateAccount(int accountBalance, int accountNumber) {
        // 1. sql 세미 콜론 넣지 말 것, ? : 바인딩
        String query = "update account_tb set account_balance = ? where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountBalance);
            statement.setInt(2, accountNumber);

            // 3. send
            int result = statement.executeUpdate();
            // 1 : 프로토콜, 다른 숫자면 insert 제대로 된 거 아님
            System.out.println("result : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. delete Account
    public void deleteAccount(int accountNumber) {
        // 1. sql 세미 콜론 넣지 말 것, ? : 바인딩
        String query = "delete from account_tb where account_number = ?";

        // 2. buffer
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountNumber);

            // 3. send
            int result = statement.executeUpdate();
            // 1 : 프로토콜, 다른 숫자면 insert 제대로 된 거 아님
            System.out.println("result : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
