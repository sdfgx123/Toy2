package dao;

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
    public void registerTeam(String name) {
        String query = "insert into team_tb (stadium_id, name) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNull(1, Types.INTEGER);
            statement.setString(2, name);

            int result = statement.executeUpdate();
            System.out.println("registerTeam res : " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 전체 팀 목록
    public List<Team> getTeamList() {

        List<Team> teamList = new ArrayList<>();
        String query = "select * from team_tb";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getInt("stadium_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
                teamList.add(team);
            }
            return teamList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }
}
