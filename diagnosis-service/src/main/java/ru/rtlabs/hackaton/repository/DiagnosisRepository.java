package ru.rtlabs.hackaton.repository;

import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisRepository extends MongoRepository<DiagnosticReport, String> {

    Optional<DiagnosticReport> findByIdMyUnqualifiedId(String id);

    Optional<DiagnosticReport> deleteByIdMyUnqualifiedId(String id);

    Optional<DiagnosticReport> findByResultId(String id);

}
