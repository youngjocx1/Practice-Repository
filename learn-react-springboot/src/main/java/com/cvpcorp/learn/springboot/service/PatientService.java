package com.cvpcorp.learn.springboot.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Order;

import org.springframework.stereotype.Service;

import com.cvpcorp.learn.springboot.dto.PatientQueryDto;
import com.cvpcorp.learn.springboot.dto.PatientResponseDtoPaginated;
import com.cvpcorp.learn.springboot.model.Condition;
import com.cvpcorp.learn.springboot.model.Encounter;
import com.cvpcorp.learn.springboot.model.Medication;
import com.cvpcorp.learn.springboot.model.Observation;
import com.cvpcorp.learn.springboot.model.Patient;

@Service
public class PatientService {

	private static final String PATIENT_ID = "patientId";

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Medication> getMedicationsByPatientId(String patientId){
		return this.entityManager.createNativeQuery("SELECT * FROM get_medications_by_patient_id(:patientId)", Medication.class).setParameter(PATIENT_ID, patientId).getResultList();
	}
	
	public List<Encounter> getEncountersByPatientId(String patientId){		
		return this.entityManager.createNativeQuery("SELECT * FROM get_encounters_by_patient_id(:patientId)", Encounter.class).setParameter(PATIENT_ID, patientId).getResultList();
	}
	
	public List<Condition> getConditionsByPatientId(String patientId){		
		return this.entityManager.createNativeQuery("SELECT * FROM get_conditions_by_patient_id(:patientId)", Condition.class).setParameter(PATIENT_ID, patientId).getResultList();
	}
	
	public List<Observation> getObservationsByPatientId(String patientId){		
		return this.entityManager.createNativeQuery("SELECT * FROM get_observations_by_patient_id(:patientId)", Observation.class).setParameter(PATIENT_ID, patientId).getResultList();
	}
	
	public List<Patient> getRelatedPatientsBySSN(String patientId, String ssn){		
		return this.entityManager.createNativeQuery("Select * FROM get_related_patients_by_ssn(:patientId, :ssn)", Patient.class).setParameter(PATIENT_ID, patientId).setParameter("ssn", ssn).getResultList();
	}
		
	public PatientResponseDtoPaginated getPaginatedPatients(Integer pageNumber, Integer maxPerPage, PatientQueryDto patientQuery){

		// Parameter Defaults
		pageNumber = (pageNumber != null ? pageNumber : 1);
		maxPerPage = (maxPerPage != null ? maxPerPage : 25);
		patientQuery = (patientQuery != null ? patientQuery : new PatientQueryDto());
				
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Patient> listCriteria = criteriaBuilder.createQuery(Patient.class);
		CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
		Root<Patient> listRoot = listCriteria.from(Patient.class);
		Root<Patient> countRoot = countCriteria.from(Patient.class);

		List<Predicate> predicateList = new ArrayList<>();
		List<Order> orderList = new ArrayList<>();

		if(patientQuery.getSsn() != null) {
			predicateList.add(criteriaBuilder.equal(listRoot.get("ssn"), patientQuery.getSsn()));
			orderList.add(criteriaBuilder.asc(listRoot.get("ssn")));
		}

		if(patientQuery.getFirstName() != null) {
			predicateList.add(criteriaBuilder.like(
					criteriaBuilder.lower(listRoot.get("firstName")),
					"%" +patientQuery.getFirstName().toLowerCase() + "%"));
		}

		if(patientQuery.getLastName() != null) {
			predicateList.add(criteriaBuilder.like(
					criteriaBuilder.lower(listRoot.get("lastName")),
					"%" +patientQuery.getLastName().toLowerCase() + "%"));
		}

		if(patientQuery.getDrivers() != null) {
			predicateList.add(criteriaBuilder.equal(
					criteriaBuilder.lower(listRoot.get("drivers")),
					patientQuery.getDrivers().toLowerCase()));
		}
		
		if(patientQuery.getState() != null) {
			predicateList.add(criteriaBuilder.equal(
					criteriaBuilder.lower(listRoot.get("state")),
					patientQuery.getState().toLowerCase()));
		}
		
		if(patientQuery.getDob() != null) {
			String[] dateParts = patientQuery.getDob().split("/");
			
			String month = dateParts[0];
			String day = dateParts[1];
			
			if(dateParts[0].substring(0, 1).equals("0")) {
				month = String.valueOf(dateParts[0].substring(1, 2));;
			}
			
			if(dateParts[1].substring(0, 1).equals("0")) {
				day = String.valueOf(dateParts[1].substring(1, 2));;
			}
			
			String formattedDate = month + "/" + day + "/" + dateParts[2];
						
			predicateList.add(criteriaBuilder.equal(
					criteriaBuilder.lower(listRoot.get("birthDate")),
					formattedDate.toLowerCase()));
		}

		if(patientQuery.getAddress() != null) {
			predicateList.add(criteriaBuilder.like(
					criteriaBuilder.lower(listRoot.get("address")),
					"%" + patientQuery.getAddress().toLowerCase() + "%"));
		}

		if(patientQuery.getCity() != null) {
			predicateList.add(criteriaBuilder.equal(
					criteriaBuilder.lower(listRoot.get("city")),
					patientQuery.getCity()
			));
		}

		Predicate[] predicates = predicateList.stream().toArray(Predicate[] ::new);
		orderList.add(criteriaBuilder.asc(listRoot.get("lastName")));
		orderList.add(criteriaBuilder.asc(listRoot.get("firstName")));
		listCriteria.select(listRoot).where(predicates);
		listCriteria.orderBy(orderList);
		Query listQuery = entityManager.createQuery(listCriteria);
		listQuery.setFirstResult( (pageNumber - 1) * maxPerPage);
		listQuery.setMaxResults(maxPerPage);
		List<Patient> resultsList = listQuery.getResultList();

		countCriteria.select(criteriaBuilder.count(countRoot)).where(predicates);
		Query countQuery = entityManager.createQuery(countCriteria);
		Long count = (Long)countQuery.getSingleResult();

		PatientResponseDtoPaginated result = new PatientResponseDtoPaginated();
		result.setPatients(resultsList);
		result.setTotalCount(BigInteger.valueOf(count));

		return result;
	}
}
