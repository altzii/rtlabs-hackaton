package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.Observation;

import java.util.List;

public interface ObservationService {


    /**
     * @param subject Reference(Patient | Group | Device | Location)
     * @return observations
     */
    List<Observation> findBySubject(String subject);


    /**
     * @return all Observation
     */
    Iterable<?> findAll();

    /**
     * @param id identifyObservation
     * @return Observation
     */
    Observation findOne(String id);

    /**
     * @param observation
     * @return Observation
     */
    Observation save(Observation observation);

    /**
     * @param id identifyObservation
     * @param observation
     * @return Observation
     */
    Observation update(String id, Observation observation);

    /**
     * @param id identifyPatient
     */
    void deleteByIdMyUnqualifiedId(String id);

}
