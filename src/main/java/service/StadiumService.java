package service;

import dao.StadiumDAO;
import db.DBConnection;
import dto.InputDTO;
import model.Stadium;

import java.sql.Connection;
import java.util.List;

public class StadiumService {

    static final Connection connetion = DBConnection.getInstance();
    static final StadiumDAO stadiumDAO = new StadiumDAO(connetion);

    public void registerStadium(InputDTO pDTO) {
        String name = pDTO.getParameters().get("name");
        stadiumDAO.registerStadium(name);
        System.out.println("성공");
    }

    public void getStadiumList() {
        //stadiumDAO.getStadiumList();
        List<Stadium> stadiumList = stadiumDAO.getStadiumList();
        System.out.println(stadiumList.toString());
        System.out.println("성공");
    }

}
