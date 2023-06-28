package dao;

import dto.TeamRespDTO;
import model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    // 팀 등록
    public void registerTeam(String stadiumId, String name) {
        String query = "insert into team_tb (stadium_id, name) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, stadiumId);
            statement.setString(2, name);

            int result = statement.executeUpdate();
            System.out.println("registerTeam res : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 전체 팀 목록
    public List<TeamRespDTO> getTeamList() {

        List<TeamRespDTO> teamList = new ArrayList<>();
        String query = "select team_tb.*, stadium_tb.name as stadium_name, stadium_tb.created_at as stadium_created_at " +
                "from team_tb " +
                "left join stadium_tb on team_tb.stadium_id = stadium_tb.id";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TeamRespDTO teamRespDTO = new TeamRespDTO(
                        rs.getInt("id"),
                        rs.getInt("stadium_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at"),
                        rs.getString("stadium_name"),
                        rs.getTimestamp("stadium_created_at")
                );
                teamList.add(teamRespDTO);
            }
            return teamList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }
}
