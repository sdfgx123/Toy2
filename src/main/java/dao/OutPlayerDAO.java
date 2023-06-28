package dao;

import model.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutPlayerDAO {
    private Connection connection;

    public OutPlayerDAO(Connection connection) {
        this.connection = connection;
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


    public List<OutPlayer> getOutPlayers() {
        List<OutPlayer> outPlayers = new ArrayList<>();
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
