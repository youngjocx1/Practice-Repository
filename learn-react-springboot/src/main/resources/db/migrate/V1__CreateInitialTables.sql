--drop table condition;

CREATE TABLE IF NOT EXISTS condition (
    esis_id VARCHAR(4000) PRIMARY KEY,
    condition_id VARCHAR(4000), 
    begin_date VARCHAR(4000) ,
    end_date VARCHAR(4000) ,
    patient_id VARCHAR(4000) ,
    condition_code VARCHAR(4000) ,
    encounter_id VARCHAR(4000) ,
    description VARCHAR(4000),
    filename VARCHAR(4000),
    unique ( begin_date,end_date,patient_id,condition_code,encounter_id,description,filename)
);

create index condition_patient on condition(patient_id);
create index condition_encounter on condition(encounter_id);

--drop table encounter;

CREATE TABLE IF NOT EXISTS encounter (
    esis_id VARCHAR(4000) PRIMARY KEY,
    encounter_id VARCHAR(4000),
    start VARCHAR(4000),
    stop VARCHAR(4000),
    provider_id VARCHAR(4000),
    patient_id VARCHAR(4000),
    organization_id VARCHAR(4000),
    payer_id VARCHAR(4000),
    encounter_class VARCHAR(4000),
    code VARCHAR(4000),
    description VARCHAR(4000),
    base_encounter_cost VARCHAR(4000),
    total_claim_cost VARCHAR(4000),
    payer_coverage VARCHAR(4000),
    reason_code VARCHAR(4000),
    reason_description VARCHAR(4000),
    filename VARCHAR(4000),
    unique (encounter_id,start,stop,provider_id,patient_id,organization_id,payer_id,encounter_class,code,description,
    base_encounter_cost,total_claim_cost,payer_coverage,reason_code,reason_description,filename)
);

create index encounter_encounter  on encounter(encounter_id);
create index encounter_patient on encounter(patient_id);

--drop table medication;

CREATE TABLE IF NOT EXISTS medication (
    esis_id VARCHAR(4000) PRIMARY KEY,
    start_date VARCHAR(4000),
    stop_date VARCHAR(4000),
    payer_id VARCHAR(4000),
    encounter_id VARCHAR(4000),
    numeric_code VARCHAR(4000),
    description VARCHAR(4000),
    base_cost VARCHAR(4000),
    payer_coverage  VARCHAR(4000),
    dispenses  VARCHAR(4000),
    total_cost  VARCHAR(4000),
    reason_code  VARCHAR(4000),
    reason_description  VARCHAR(4000),
    filename  VARCHAR(4000),    
    unique (start_date,stop_date,payer_id,encounter_id,numeric_code,description, base_cost,
    payer_coverage,dispenses,total_cost,reason_code,reason_description,filename)
);

create index medication_encounter on medication(encounter_id);
--drop table observation;

CREATE TABLE IF NOT EXISTS observation (
    esis_id VARCHAR(4000) PRIMARY KEY,
    date VARCHAR(4000),
    encounter_id VARCHAR(4000),
    description VARCHAR(4000),
    code VARCHAR(4000),
    value VARCHAR(4000),
    units VARCHAR(4000),
    unit_type VARCHAR(4000),
    filename VARCHAR(4000),
    unique (date,encounter_id,description,code,value,units,unit_type,filename)
);

create index observation_encounter on observation (encounter_id);

--drop table patient;

CREATE TABLE IF NOT EXISTS patient (
    esis_id VARCHAR(4000) PRIMARY KEY,
    patient_id VARCHAR(4000),
    birthdate VARCHAR(4000),
    deathdate VARCHAR(4000),
    social_security_number VARCHAR(4000),
    drivers VARCHAR(4000),
    passport VARCHAR(4000),
    prefix VARCHAR(4000),
    first_name VARCHAR(4000),
    surname VARCHAR(4000),
    suffix VARCHAR(4000),
    maiden_name VARCHAR(4000),
    marital VARCHAR(4000),
    race VARCHAR(4000),
    ethnicity VARCHAR(4000),
    gender VARCHAR(4000),
    birthplace VARCHAR(4000),
    address VARCHAR(4000),
    postal_code VARCHAR(4000),
    city VARCHAR(4000),
    state VARCHAR(4000),
    county VARCHAR(4000),
    lat VARCHAR(4000),
    lon VARCHAR(4000),
    healthcare_coverage VARCHAR(4000),
    healthcare_expenses VARCHAR(4000),
    filename VARCHAR(4000),
    unique (patient_id, birthdate, deathdate, social_security_number, drivers, passport,
			prefix, first_name, surname, suffix, maiden_name, marital, race, ethnicity,
			gender, birthplace, address, postal_code, city, state, county, lat, lon,
			healthcare_coverage, healthcare_expenses,filename)
	);

create index patient_patient on patient (patient_id);
create index patient_ssn on patient(social_security_number);

create or replace view customer as
select patient_id, social_security_number, birthdate,
case when row_number()
over (partition by social_security_number order by birthdate asc nulls last ) =1
then 'Y' else 'N' end as primary,
row_number() over (partition by social_security_number order by birthdate asc nulls last ) rn,
count(*) over (partition by social_security_number  ) total_dupes
from patient;

CREATE TABLE IF NOT EXISTS error (
	esis_id VARCHAR(4000) PRIMARY KEY,
    error_type VARCHAR(4000),
    error_message VARCHAR(65535)
);

CREATE OR REPLACE FUNCTION public.get_conditions_by_patient_id(
	patientid character varying)
    RETURNS SETOF condition 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	Return Query Select * FROM condition c
	WHERE c.encounter_id IN (SELECT e.encounter_id FROM encounter e WHERE e.patient_id = patientId);
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.get_encounters_by_patient_id(
	patientid character varying)
    RETURNS SETOF encounter 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	Return Query Select * FROM encounter WHERE patient_id = patientId;
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.get_medications_by_patient_id(
	patientid character varying)
    RETURNS SETOF medication 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	Return Query Select * FROM medication m 
	WHERE m.encounter_id IN (SELECT e.encounter_id FROM encounter e WHERE e.patient_id = patientId);
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.get_observations_by_patient_id(
	patientid character varying)
    RETURNS SETOF observation 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	Return Query Select * FROM observation o
	WHERE o.encounter_id IN (SELECT e.encounter_id FROM encounter e WHERE e.patient_id = patientId);
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.get_related_patients_by_ssn(
	patientid character varying,
	ssn character varying)
    RETURNS SETOF patient 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	Return Query SELECT * FROM patient where social_security_number = ssn and patient_id != patientid Order By Cast(birthdate AS Date);
END;
$BODY$;