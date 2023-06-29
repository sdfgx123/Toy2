package service;

import dao.TeamDAO;
import db.DBConnection;
import dto.InputDTO;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.util.List;

public class TeamService {

    static final Connection connection = DBConnection.getInstance();
    static final TeamDAO teamDAO = TeamDAO.getInstance(connection);

    private static final TeamService instance = new TeamService();

    private TeamService() {

    }

    public static TeamService getInstance() {
        return instance;
    }

    public void registerTeam(InputDTO pDTO) {
        String stadiumId = pDTO.getParameters().get("stadiumId");
        String name = pDTO.getParameters().get("name");
        teamDAO.registerTeam(stadiumId, name);
        System.out.println("\n--------");
        System.out.println("팀 등록 성공");
        System.out.println("--------\n");

    }

    public void getTeamList() {
        List<TeamRespDTO> teamList = teamDAO.getTeamList();

        System.out.printf("%-6s | %-12s | %-25s | %-35s | %-25s | %-35s%n",
                "ID", "Stadium ID", "팀 이름", "생성 날짜", "Stadium 이름", "Stadium 생성 날짜");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for (TeamRespDTO team : teamList) {
            System.out.printf("%-6d | %-12d | %-25s | %-35s | %-25s | %-35s%n",
                    team.getId(),
                    team.getStadiumId(),
                    team.getName(),
                    team.getCreatedAt(),
                    team.getStadiumName(),
                    team.getStadiumCreatedAt());
        }
    }


}
