import dao.AccountDAO;
import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.util.List;

public class BaseBallApp {

    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        AccountDAO accountDAO = new AccountDAO(connection);

        List<Account> accountList = accountDAO.getAccountList();
        System.out.println(accountList.toString());

    }
}
