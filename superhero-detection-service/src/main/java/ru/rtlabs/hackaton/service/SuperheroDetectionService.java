package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.exceptions.FHIRException;

public interface SuperheroDetectionService {

    /**
     * @param observation
     */
    void superHeroDetectionAndUpdate(Observation observation) throws FHIRException;

}
