package model;

import java.sql.Timestamp;

public class OutPlayer {
    private int id;
    private int player_id;
    private String reason;
    private Timestamp create_at;

    public OutPlayer(int id, int player_id, String reason, Timestamp create_at) {
        this.id = id;
        this.player_id = player_id;
        this.reason = reason;
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "OutPlayer{" +
                "id=" + id +
                ", player_id=" + player_id +
                ", reason='" + reason + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
