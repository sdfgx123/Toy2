package service;

import dao.TeamDAO;
import db.DBConnection;
import dto.InputDTO;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.util.List;

public class TeamService {

    static final Connection connection = DBConnection.getInstance();
    static final TeamDAO teamDAO = new TeamDAO(connection);

    public void registerTeam(InputDTO pDTO) {
        String stadiumId = pDTO.getParameters().get("stadiumId");
        String name = pDTO.getParameters().get("name");
        teamDAO.registerTeam(stadiumId, name);
        System.out.println("성공");
    }

    public void getTeamList() {
        List<TeamRespDTO> teamList = teamDAO.getTeamList();
        System.out.println(teamList.toString());
        System.out.println("성공");
    }


}
