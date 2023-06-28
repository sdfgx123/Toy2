import dao.*;
import db.DBConnection;
import dto.InputDTO;
import model.Account;
import model.Stadium;
import model.Team;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {

//    static final Connection connection = DBConnection.getInstance();
//    static final AccountDAO accountDAO = new AccountDAO(connection);
//    static final StadiumDAO stadiumDAO = new StadiumDAO(connection);
//    static final TeamDAO teamDAO = new TeamDAO(connection);

    //static final StadiumService stadiumService = new StadiumService();
    //static final TeamService teamService = new TeamService();

    static final TeamService teamService = TeamService.getInstance();
    static final StadiumService stadiumService = StadiumService.getInstance();

    public static void main(String[] args) {
//        List<Account> accountList = accountDAO.getAccountList();
////        System.out.println(accountList.toString());
////
////        List<Stadium> stadiumList = stadiumDAO.getStadiumList();
////        System.out.println(stadiumList.toString());
////
////        List<Team> teamList = teamDAO.getTeamList();
////        System.out.println(teamList.toString());

//        System.out.println("-----registerStadium-----");
//        stadiumDAO.registerStadium("test-001");
//        System.out.println(stadiumList.toString());

        InputDTO pDTO = Input.makeRequest();
        callFunction(pDTO);

    }

    public static void callFunction(InputDTO pDTO) {
        String methodName = pDTO.getMethodName();
        if (methodName.equals("야구장등록")) stadiumService.registerStadium(pDTO);
        if (methodName.equals("야구장목록")) stadiumService.getStadiumList();
        if (methodName.equals("팀등록")) teamService.registerTeam(pDTO);
        if (methodName.equals("팀목록")) teamService.getTeamList();
//        if (methodName.equals("선수등록"))
//        if (methodName.equals("선수목록"))
//        if (methodName.equals("퇴출등록"))
//        if (methodName.equals("퇴출목록"))
//        if (methodName.equals("포지션별목록"))
    }
}
