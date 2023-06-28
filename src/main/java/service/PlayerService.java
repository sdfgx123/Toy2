package service;

import dao.PlayerDAO;
import db.DBConnection;
import dto.InputDTO;
import model.Player;

import java.sql.Connection;
import java.util.List;

public class PlayerService {
    private static PlayerService playerService;
    private static Connection connection = DBConnection.getInstance();
    //싱글톤
    private static PlayerDAO playerDAO;

    public static PlayerService getInstance() {
        if(playerService==null){
            playerService=new PlayerService();
        }
        return playerService;
    }

    public int registerPlayer(InputDTO pDTO){
        return 0;
    }

    public void getPlayers(InputDTO pDTO){
        int teamId = Integer.parseInt(pDTO.getParameters().get("teamId"));
        List<Player> players = playerDAO.getPlayers(teamId);
        System.out.println(players);
    }
}
