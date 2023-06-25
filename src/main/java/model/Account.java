package model;

import lombok.Getter;

import java.sql.Timestamp;

/**
 * 왜 private으로 선언하는걸까?
 * 필드에 직접 접근하지 않기 위해서 ->
 */
@Getter
public class Account {
    private int accountNumber;
    private String accountPassword;
    private int accountBalance;
    private Timestamp accountCreatedAt;

    public Account(int accountNumber, String accountPassword, int accountBalance, Timestamp accountCreatedAt) {
        this.accountNumber = accountNumber;
        this.accountPassword = accountPassword;
        this.accountBalance = accountBalance;
        this.accountCreatedAt = accountCreatedAt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountBalance=" + accountBalance +
                ", accountCreatedAt=" + accountCreatedAt +
                '}';
    }
}
