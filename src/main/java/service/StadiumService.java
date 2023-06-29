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

        System.out.printf("%-5s | %-20s | %-25s%n", "ID", "이름", "생성 날짜");
        System.out.println("---------------------------------------------------");

        for (Stadium stadium : stadiumList) {
            System.out.printf("%-5d | %-20s | %-25s%n",
                    stadium.getId(),
                    stadium.getName(),
                    stadium.getCreatedAt());
        }

//        System.out.println(stadiumList);
//        System.out.println("성공");
    }

}
