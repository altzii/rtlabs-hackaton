package ru.rtlabs.hackaton.service;

import ca.uhn.fhir.model.primitive.IdDt;
import org.hl7.fhir.dstu3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtlabs.hackaton.repository.ObservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ObservationServiceImpl implements ObservationService {

    private final ObservationRepository observationRepository;
    private final static String OBSERVATION_RESOURCE_NAME = "Observation";

    @Autowired
    public ObservationServiceImpl(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    public List<Observation> findBySubject(String subject) {
        Observation observation = new Observation();
        observation.setSubject(new Reference());
        return observationRepository.findBySubjectReference(subject);
    }

    @Override
    public Iterable<?> findAll() {
        return observationRepository.findAll();
    }

    @Override
    public Observation findOne(String id) {
        Optional<Observation> observation = observationRepository.getByIdMyUnqualifiedId(id);
        return observation.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Observation save(Observation observation) {
        UUID uid = UUID.randomUUID();
        String idPart = new IdDt(uid.toString()).getValue();
        observation.setId(OBSERVATION_RESOURCE_NAME + '/' + idPart);
        observation.setStatus(Observation.ObservationStatus.FINAL);
        return observationRepository.save(observation);
    }

    @Override
    public Observation update(String id, Observation observation) {
        Optional<Observation> optionalObservation= observationRepository.getByIdMyUnqualifiedId(id);
        Observation update = optionalObservation.orElseThrow(IllegalArgumentException::new);
        update.setId(observation.getId());
        update.setStatus(Observation.ObservationStatus.FINAL);
        update.setCode(new CodeableConcept());
        update.setIssued(new Date());
        update.setComponent(observation.getComponent());
        update.setSubject(observation.getSubject());
        return observationRepository.save(update);
    }

    @Override
    public void deleteByIdMyUnqualifiedId(String id) {
        observationRepository.deleteByIdMyUnqualifiedId(id);
    }
}
