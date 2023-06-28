package service;

import dao.StadiumDAO;
import db.DBConnection;
import dto.InputDTO;
import model.Stadium;

import java.sql.Connection;
import java.util.List;

public class StadiumService {

    static final Connection connection = DBConnection.getInstance();
    static final StadiumDAO stadiumDAO = StadiumDAO.getInstance(connection);

    private static final StadiumService instance = new StadiumService();

    private StadiumService() {

    }

    public static StadiumService getInstance() {
        return instance;
    }

    public void registerStadium(InputDTO pDTO) {
        String name = pDTO.getParameters().get("name");
        stadiumDAO.registerStadium(name);
        System.out.println("성공");
    }

    public void getStadiumList() {
        List<Stadium> stadiumList = stadiumDAO.getStadiumList();
        System.out.println(stadiumList);
        System.out.println("성공");
    }

}
