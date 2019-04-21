package ru.rtlabs.hackaton.repository;

import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObservationRepository extends MongoRepository<Observation, String> {
    List<Observation> findBySubjectReference(String subject);
    Optional<Observation> getByIdMyUnqualifiedId(String id);
    void deleteByIdMyUnqualifiedId(String id);
}
