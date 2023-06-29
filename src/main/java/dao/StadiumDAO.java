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
    private static StadiumDAO instance;

    private StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    public static StadiumDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new StadiumDAO(connection);
        }
        return instance;
    }

    // 야구장 등록
    public void registerStadium(String name) {

        String query = "insert into stadium_tb (name) values (?)";

        try {
            if (name == null) {
                throw new IllegalArgumentException();
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);

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
