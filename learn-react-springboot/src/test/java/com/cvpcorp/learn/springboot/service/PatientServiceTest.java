package com.cvpcorp.learn.springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cvpcorp.learn.springboot.dto.PatientQueryDto;
import com.cvpcorp.learn.springboot.dto.PatientResponseDtoPaginated;
import com.cvpcorp.learn.springboot.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService service;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder mockCriteriaBuilder;

    @Mock
    CriteriaQuery mockListCriteria;

    @Mock
    CriteriaQuery mockCountCriteria;

    @Mock
    Root<Patient> mockListRoot;

    @Mock
    Root<Patient> mockCountRoot;

    @Mock
    TypedQuery<Patient> mockListQuery;

    @Mock
    TypedQuery<Long> mockCountQuery;

    private List<Patient> mockSearchResults = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        when(entityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
        when(entityManager.createQuery(mockListCriteria)).thenReturn(mockListQuery);
        when(entityManager.createQuery(mockCountCriteria)).thenReturn(mockCountQuery);
        when(mockCriteriaBuilder.createQuery(Patient.class)).thenReturn(mockListCriteria);
        when(mockCriteriaBuilder.createQuery(Long.class)).thenReturn(mockCountCriteria);
        when(mockListCriteria.from(Patient.class)).thenReturn(mockListRoot);
        when(mockListCriteria.select(any())).thenReturn(mockListCriteria);
        when(mockCountCriteria.from(Patient.class)).thenReturn(mockCountRoot);
        when(mockCountCriteria.select(any())).thenReturn(mockCountCriteria);
        when(mockListQuery.getResultList()).thenReturn(mockSearchResults);
        when(mockCountQuery.getSingleResult()).thenReturn(1L);
    }

    @Test
    void getPaginatedPatients() throws Exception {
        PatientQueryDto patientQuery = new PatientQueryDto();
        patientQuery.setFirstName("Tester");
        patientQuery.setLastName("McTester");
        patientQuery.setAddress("123 Testing Lane");
        patientQuery.setDrivers("FL-123-4567890");
        patientQuery.setSsn("123-45-6789");
        patientQuery.setDob("04/27/1970");

        mockSearchResults.add(new Patient());

        PatientResponseDtoPaginated response =
                service.getPaginatedPatients(1, 25, patientQuery);
        assertEquals(1, response.getPatients().size());
    }

    @Test
    void getPaginatedPatients_AllCriteriaNull() throws Exception {
        PatientResponseDtoPaginated response =
                service.getPaginatedPatients(null, null, new PatientQueryDto());
        assertEquals(0, response.getPatients().size());
    }
}
