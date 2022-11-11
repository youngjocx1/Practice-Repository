package com.cvpcorp.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ObservationDto {

    private String id;
    private String date;
    private String description;
    private String code;
    private String value;
    private String units;
    private String unitType;
    private String encounterId;
}
