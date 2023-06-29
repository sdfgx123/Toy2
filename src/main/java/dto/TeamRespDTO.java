package dto;

import java.sql.Timestamp;

public class TeamRespDTO {

    int id;
    int stadiumId;
    String name;
    Timestamp createdAt;
    String stadiumName;
    Timestamp stadiumCreatedAt;

    public TeamRespDTO(int id, int stadiumId, String name, Timestamp createdAt, String stadiumName, Timestamp stadiumCreatedAt) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.name = name;
        this.createdAt = createdAt;
        this.stadiumName = stadiumName;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }

    @Override
    public String toString() {
        return "TeamRespDTO{" +
                "id=" + id +
                ", stadiumId=" + stadiumId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", stadiumName='" + stadiumName + '\'' +
                ", stadiumCreatedAt=" + stadiumCreatedAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public Timestamp getStadiumCreatedAt() {
        return stadiumCreatedAt;
    }
}
