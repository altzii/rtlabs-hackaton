package ru.rtlabs.hackaton.repository;

import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperheroDetectionRepository extends MongoRepository<DiagnosticReport, String> {
    Patient findByIdMyUnqualifiedId(String id);


}
