package com.cvpcorp.learn.springboot.maps;

import com.cvpcorp.learn.springboot.dto.ConditionDto;
import com.cvpcorp.learn.springboot.model.Condition;

public class ConditionMap {

    private ConditionMap() {}

	public static Condition map(ConditionDto dto) {
        Condition condition = new Condition();
        condition.setId(dto.getId());
        condition.setBeginDate(dto.getBeginDate());
        condition.setEndDate(dto.getEndDate());
        condition.setConditionCode(dto.getConditionCode());
        condition.setDescription(dto.getDescription());
        condition.setEncounterId(dto.getEncounterId());

        return condition;
    }

	public static ConditionDto map(Condition condition) {
        ConditionDto dto = new ConditionDto();
        dto.setId(condition.getId());
        dto.setBeginDate(condition.getBeginDate());
        dto.setEndDate(condition.getEndDate());
        dto.setConditionCode(condition.getConditionCode());
        dto.setDescription(condition.getDescription());
        dto.setEncounterId(condition.getEncounterId());

        return dto;
    }
}
