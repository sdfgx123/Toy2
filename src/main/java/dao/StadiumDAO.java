package dao;

import model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {

    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    // 야구장 등록
    public void registerStadium() {

    }

    // 전체 야구장 목록 보기
    public List<Stadium> getStadiumList() {

        List<Stadium> stadiumList = new ArrayList<>();

        String query = "select * from stadium_tb";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Stadium stadium = new Stadium(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")
                );
                stadiumList.add(stadium);
            }
            return stadiumList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadiumList;
    }
}
