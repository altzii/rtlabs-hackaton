package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.DiagnosticReport;

public interface DiagnosisService {

    /**
     * @return DiagnosticReport
     */
    Iterable<?> findAll();

    /**
     * @param id identifyDiagnosticReport
     * @return DiagnosticReport
     */
    DiagnosticReport findByIdMyUnqualifiedId(String id);

    /**
     * @param diagnosticReport
     * @return DiagnosticReport
     */
    DiagnosticReport save(DiagnosticReport diagnosticReport);

    /**
     * @param id identifyDiagnosticReport
     * @param diagnosticReport
     * @return DiagnosticReport
     */
    DiagnosticReport update(String id, DiagnosticReport diagnosticReport);

    /**
     * @param id identifyDiagnosticReport
     */
    void deleteByIdMyUnqualifiedId(String id);

    /**
     * @param id identifyObservation
     * @return DiagnosticReport
     */
    DiagnosticReport findByObservationId(String id);

}
