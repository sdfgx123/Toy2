package model;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Stadium {

    private int id;
    private String name;
    private Timestamp createdAt;
}
