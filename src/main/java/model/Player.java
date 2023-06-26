package model;

import java.sql.Timestamp;

public class Player {
    private int id;
    private int team_id;
    private String name;
    private String position;
    private Timestamp createAt;

    public Player(int id, int team_id, String name, String position, Timestamp createAt) {
        this.id = id;
        this.team_id = team_id;
        this.name = name;
        this.position = position;
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", team_id=" + team_id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
