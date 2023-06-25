import dao.AccountDAO;
import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBConnection;
import model.Account;
import model.Stadium;
import model.Team;

import java.sql.Connection;
import java.util.List;

public class BaseBallApp {

    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        AccountDAO accountDAO = new AccountDAO(connection);
        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);

        List<Account> accountList = accountDAO.getAccountList();
        System.out.println(accountList.toString());

        List<Stadium> stadiumList = stadiumDAO.getStadiumList();
        System.out.println(stadiumList.toString());

        List<Team> teamList = teamDAO.getTeamList();
        System.out.println(teamList.toString());



    }
}
