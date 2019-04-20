package ru.rtlabs.hackaton.repository;

import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Optional<Patient> findByIdMyUnqualifiedId(String id);

    Optional<Patient> deleteByIdMyUnqualifiedId(String id);

}
