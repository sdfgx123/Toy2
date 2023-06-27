import dao.*;
import db.DBConnection;
import model.Account;
import model.Stadium;
import model.Team;

import java.sql.Connection;
import java.util.List;

public class BaseBallApp {

    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        PlayerDAO playerDAO = new PlayerDAO(connection);

        playerDAO.registerPlayer(1, "오형석", "유격수");

        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
        outPlayerDAO.registerOutPlayer(20, "도박");
        System.out.println(playerDAO.getPlayers(1));
        System.out.println(outPlayerDAO.getOutPlayers());

//
//        AccountDAO accountDAO = new AccountDAO(connection);
//        StadiumDAO stadiumDAO = new StadiumDAO(connection);
//        TeamDAO teamDAO = new TeamDAO(connection);
//
//        List<Account> accountList = accountDAO.getAccountList();
//        System.out.println(accountList.toString());
//
//        List<Stadium> stadiumList = stadiumDAO.getStadiumList();
//        System.out.println(stadiumList.toString());
//
//        List<Team> teamList = teamDAO.getTeamList();
//        System.out.println(teamList.toString());
//
//        System.out.println("-----registerStadium-----");
//        stadiumDAO.registerStadium("test-001");
//        System.out.println(stadiumList.toString());


    }
}
