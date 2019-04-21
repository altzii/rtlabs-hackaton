package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.Patient;

public interface PatientService {

    /**
     * @return Patients
     */
    Iterable<?> findAll();

    /**
     * @param id identifyPatient
     * @return Patient
     */
    Patient findByIdMyUnqualifiedId(String id);

    /**
     * @param patient
     * @return Patient
     */
    Patient save(Patient patient);

    /**
     * @param id identifyPatient
     * @param patient
     * @return Patient
     */
    Patient update(String id, Patient patient);

    /**
     * @param id identifyPatient
     */
    void deleteByIdMyUnqualifiedId(String id);

}
