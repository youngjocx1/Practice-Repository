package com.cvpcorp.learn.springboot.maps;

import com.cvpcorp.learn.springboot.dto.EncounterDto;
import com.cvpcorp.learn.springboot.model.Encounter;

public class EncounterMap {

    private EncounterMap() {}

	public static EncounterDto map(Encounter encounter) {
        EncounterDto dto = new EncounterDto();
        dto.setEsisId(encounter.getEsisId());
        dto.setEncounterId(encounter.getEncounterId());
        dto.setDescription(encounter.getDescription());
        dto.setEncounterCode(encounter.getEncounterCode());
        dto.setStart(encounter.getStart());
        dto.setStop(encounter.getStop());
        dto.setReasonCode(encounter.getReasonCode());
        dto.setReasonDescription(encounter.getReasonDescription());
        dto.setPatientId(encounter.getPatientId());
        dto.setBaseEncounterCost(encounter.getBaseEncounterCost());
        dto.setEncounterClass(encounter.getEncounterClass());
        dto.setTotalClaimCost(encounter.getTotalClaimCost());
        dto.setPayerCoverage(encounter.getPayerCoverage());

        return dto;
    }

	public static Encounter map(EncounterDto dto) {
        Encounter encounter = new Encounter();
        encounter.setEsisId(dto.getEsisId());
        encounter.setEncounterId(dto.getEncounterId());
        encounter.setDescription(dto.getDescription());
        encounter.setEncounterCode(dto.getEncounterCode());
        encounter.setStart(dto.getStart());
        encounter.setStop(dto.getStop());
        encounter.setReasonCode(dto.getReasonCode());
        encounter.setReasonDescription(dto.getReasonDescription());
        encounter.setPatientId(dto.getPatientId());
        encounter.setBaseEncounterCost(dto.getBaseEncounterCost());
        encounter.setEncounterClass(dto.getEncounterClass());
        encounter.setTotalClaimCost(dto.getTotalClaimCost());
        encounter.setPayerCoverage(dto.getPayerCoverage());

        return encounter;
    }
}
