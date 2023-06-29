package dao;

import dto.PositionRespDTO;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PlayerDAO {

    private static PlayerDAO playerDAO;
    private static Connection connection;

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance(Connection connection) {
        if (playerDAO == null) {
            PlayerDAO.connection = connection;
            playerDAO = new PlayerDAO();
        }
        return playerDAO;
    }

    public void registerPlayer(int teamId, String name, String position) {
        String query = "insert into player_tb (team_id, name, position, created_at) values (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            statement.setTimestamp(4, new Timestamp(new Date().getTime()));

            int result = statement.executeUpdate();
            System.out.println("register Player result= " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayers(int teamId) {
        List<Player> players = new ArrayList<>();
        String query = "select * from player_tb where team_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                players.add(player);
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PositionRespDTO> getPlayersOfPosition() {
        List<PositionRespDTO> players = new ArrayList<>();
        String query = "select \n" +
                "p.position as '포지션'," +
                "max(case when t.name='두산' then p.name end) as '두산'," +
                "max(case when t.name='넥센' then p.name end) as '넥센'," +
                "max(case when t.name='NC' then p.name end) as 'NC'," +
                "max(case when t.name='기아' then p.name end) as '기아'," +
                "max(case when t.name='SK' then p.name end) as 'SK'" +
                "from team_tb t inner join player_tb p on t.id = p.team_id where p.position='1루수' or p.position='2루수'" +
                "group by p.position;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PositionRespDTO positionRespDTO = new PositionRespDTO(
                        rs.getString("포지션"),
                        rs.getString("두산"),
                        rs.getString("넥센"),
                        rs.getString("NC"),
                        rs.getString("기아"),
                        rs.getString("SK")
                );
                players.add(positionRespDTO);
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
