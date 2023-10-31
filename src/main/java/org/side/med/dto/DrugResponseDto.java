package org.side.med.dto;

import lombok.Data;

@Data
public class DrugResponseDto {
    private String resultCode;
    private String resultMsg;
    private String numOfRows;

}
