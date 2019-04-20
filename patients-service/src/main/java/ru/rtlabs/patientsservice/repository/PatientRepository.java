package ru.rtlabs.patientsservice.repository;

import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient,String> {
    Optional<Patient> findById(String id);

}
