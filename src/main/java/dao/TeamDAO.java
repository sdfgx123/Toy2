package dao;

import dto.TeamRespDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private Connection connection;
    private static TeamDAO instance;

    private TeamDAO(Connection connection) {
        this.connection = connection;
    }

    public static TeamDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new TeamDAO(connection);
        }
        return instance;
    }

    public boolean registerTeam(String stadiumId, String name) {
        String query = "insert into team_tb (stadium_id, name) values (?, ?)";

        try {
            if (stadiumId == null || name == null) {
                throw new IllegalArgumentException();
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, stadiumId);
            statement.setString(2, name);

            int result = statement.executeUpdate();
            return result > 0;

        } catch (IllegalArgumentException e) {
            System.out.println("유효하지 않은 파라미터 입니다. 요청 파라미터를 확인하십시오.");
            return false;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("데이터베이스 무결성 제약 조건에 위배됩니다. 요청 파라미터를 확인하십시오.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("요청에 문제가 있습니다. 요청 내용을 확인하십시오.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("예기치 않은 오류가 발생했습니다. 요청을 다시 확인 후 재시도 하십시오.");
            return false;
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
