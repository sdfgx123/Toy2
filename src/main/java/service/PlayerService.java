package service;

import dao.PlayerDAO;
import db.DBConnection;
import dto.InputDTO;
import model.Player;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private static PlayerService playerService;
    private static Connection connection = DBConnection.getInstance();
    //싱글톤
    private static PlayerDAO playerDAO;

    private PlayerService() {
        playerDAO = PlayerDAO.getInstance(connection);
    }

    public static PlayerService getInstance() {
        if (playerService == null) {
            playerService = new PlayerService();
        }
        return playerService;
    }

    public void registerPlayer(InputDTO pDTO) {
        Map<String, String> params = pDTO.getParameters();
        int teamId = Integer.parseInt(params.get("teamId"));
        String name = params.get("name");
        String position = params.get("position");
        playerDAO.registerPlayer(teamId, name, position);
    }

    public void getPlayers(InputDTO pDTO) {
        int teamId = Integer.parseInt(pDTO.getParameters().get("teamId"));
        List<Player> players = playerDAO.getPlayers(teamId);
        System.out.println(players);
    }
}
