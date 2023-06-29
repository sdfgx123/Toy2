import dao.*;
import db.DBConnection;
import dto.InputDTO;
import model.Account;
import model.Stadium;
import model.Team;
import service.OutPlayerService;
import service.PlayerService;
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


    static final TeamService teamService = TeamService.getInstance();
    static final StadiumService stadiumService = StadiumService.getInstance();
    private static final PlayerService playerService = PlayerService.getInstance();
    private static final OutPlayerService outPlayerService = OutPlayerService.getInstance();

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

        while (true) {
            InputDTO pDTO = Input.makeRequest();
            callFunction(pDTO);
            System.out.println("\n-----------------------------------------------------");
            System.out.println("요청에 대한 처리가 모두 끝났습니다. 기능 요청 콘솔을 다시 출력합니다.");
            System.out.println("-----------------------------------------------------\n");
        }
    }

    public static void callFunction(InputDTO pDTO) {
        String methodName = pDTO.getMethodName();
        if (methodName.equals("야구장등록")) stadiumService.registerStadium(pDTO);
        if (methodName.equals("야구장목록")) stadiumService.getStadiumList();
        if (methodName.equals("팀등록")) teamService.registerTeam(pDTO);
        if (methodName.equals("팀목록")) teamService.getTeamList();
        if (methodName.equals("선수등록")) playerService.registerPlayer(pDTO);
        if (methodName.equals("선수목록")) playerService.getPlayers(pDTO);
        if (methodName.equals("퇴출등록")) outPlayerService.registerOutPlayer(pDTO);
        if (methodName.equals("퇴출목록")) outPlayerService.getOutPlayers();
        if (methodName.equals("포지션별목록")) playerService.getPlayersOfPosition();
    }
}
