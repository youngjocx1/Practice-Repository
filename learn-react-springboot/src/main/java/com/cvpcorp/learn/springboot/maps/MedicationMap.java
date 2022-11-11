package com.cvpcorp.learn.springboot.maps;

import com.cvpcorp.learn.springboot.dto.MedicationDto;
import com.cvpcorp.learn.springboot.model.Medication;

public class MedicationMap {

    private MedicationMap() {}

	public static MedicationDto map(Medication medication) {
        MedicationDto dto = new MedicationDto();
        dto.setId(medication.getId());
        dto.setStartDate(medication.getStartDate());
        dto.setStopDate(medication.getStopDate());
        dto.setDescription(medication.getDescription());
        dto.setNumericCode(medication.getNumericCode());
        dto.setEncounterId(medication.getEncounterId());

        return dto;
    }

    public static Medication map(MedicationDto dto) {
        Medication medication = new Medication();
        medication.setId(dto.getId());
        medication.setStartDate(dto.getStartDate());
        medication.setStopDate(dto.getStopDate());
        medication.setDescription(dto.getDescription());
        medication.setNumericCode(dto.getNumericCode());
        medication.setEncounterId(dto.getEncounterId());

        return medication;
    }
}
