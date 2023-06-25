package model;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Team {

    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
}
