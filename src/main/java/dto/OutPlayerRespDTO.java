package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
public class OutPlayerRespDTO {
    private int id;
    private String name;
    private String position;
    private String reason;
    private Timestamp day;
}
