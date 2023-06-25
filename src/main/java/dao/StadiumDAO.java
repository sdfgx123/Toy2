package dao;

import java.sql.Connection;

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

    }
}
