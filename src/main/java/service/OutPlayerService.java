package service;

import dao.OutPlayerDAO;
import db.DBConnection;
import dto.InputDTO;
import dto.OutPlayerRespDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class OutPlayerService {
    private static OutPlayerService outPlayerService;
    private static Connection connection = DBConnection.getInstance();
    private OutPlayerDAO outPlayerDAO;

    private OutPlayerService() {
        outPlayerDAO = OutPlayerDAO.getInstance(connection);
    }

    public static OutPlayerService getInstance() {
        if (outPlayerService == null) {
            outPlayerService = new OutPlayerService();
        }
        return outPlayerService;
    }

    public void registerOutPlayer(InputDTO pDTO) {
        Map<String, String> params = pDTO.getParameters();
        int playerId = Integer.parseInt(params.get("playerId"));
        String reason = params.get("reason");
        outPlayerDAO.registerOutPlayer(playerId, reason);
    }


    public void getOutPlayers(){
        List<OutPlayerRespDTO> outPlayers = outPlayerDAO.getOutPlayers();
        System.out.println(outPlayers);
    }
}
