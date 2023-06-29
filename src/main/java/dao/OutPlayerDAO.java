package dao;

import dto.OutPlayerRespDTO;
import model.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutPlayerDAO {
    private static OutPlayerDAO outPlayerDAO;
    private static Connection connection;

    private OutPlayerDAO() {
    }

    public static OutPlayerDAO getInstance(Connection connection) {
        if (outPlayerDAO == null) {
            OutPlayerDAO.connection = connection;
            outPlayerDAO = new OutPlayerDAO();
        }
        return outPlayerDAO;
    }

    public void registerOutPlayer(int playerId, String reason) {
        String insertQuery = "insert into out_player_tb (player_id, reason, created_at) values (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.setTimestamp(3, new Timestamp(new Date().getTime()));

            int result = statement.executeUpdate();
            updateOutedPlayer(playerId);
            System.out.println("register OutPlayer result= " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateOutedPlayer(int id) {
        String updateQuery = "update player_tb set team_id = null where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, id);

            int result = statement.executeUpdate();
            System.out.println("updateResult = " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<OutPlayerRespDTO> getOutPlayers() {
        List<OutPlayerRespDTO> outPlayers = new ArrayList<>();
        String query = "select p.id, p.name, p.position, o.reason, o.created_at " +
                "from player_tb p left join out_player_tb o on p.id = o.player_id order by p.id;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OutPlayerRespDTO outPlayer = new OutPlayerRespDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
                outPlayers.add(outPlayer);
            }
            return outPlayers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OutPlayer getOutPlayer() {
        String query = "select * from out_player_tb";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OutPlayer outPlayer = new OutPlayer(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
                return outPlayer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
