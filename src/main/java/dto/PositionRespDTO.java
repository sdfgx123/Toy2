package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class PositionRespDTO {
    private String position;
    private String teamDoosan;
    private String teamNexen;
    private String teamNC;
    private String teamKia;
    private String teamSK;
}
