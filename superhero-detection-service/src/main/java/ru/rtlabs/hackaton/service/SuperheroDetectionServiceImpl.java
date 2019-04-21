package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.exceptions.FHIRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperheroDetectionServiceImpl implements SuperheroDetectionService {

    private static final String PATIENT_SERVICE = "http://localhost:8080/patients-service/patient";
    private static final String DIAGNOSIS_REPORT_SERVICE = "http://localhost:8080//diagnosis-service/diagnostic";
    private final RestTemplate restTemplate;

    @Autowired
    public SuperheroDetectionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public void superHeroDetectionAndUpdate(Observation observation) throws FHIRException {

        String patientId = observation.getSubject().getReference().split("\\/")[1];
        Patient patient = restTemplate.getForObject(PATIENT_SERVICE + "/" + patientId, Patient.class);
        String surname = patient.getName().get(0).getFamily();

        if (!"Лукьянов".equals(surname) && !"Фиоктистов".equals(surname) && !"Фасихов".equals(surname)) {
            return;
        }
        List<StringType> superName = new ArrayList<>();
        superName.add(new StringType(patient.getName().get(0).getGiven().get(0) + "-Супергерой"));
        patient.getName().get(0).setGiven(superName);
        restTemplate.put(PATIENT_SERVICE + "/" + patientId, patient);

        DiagnosticReport diagnosticReport = new DiagnosticReport();
        diagnosticReport.setId("1");
        CodeableConcept codeableConcept = new CodeableConcept();
        codeableConcept.setId("Супергерой");
        diagnosticReport.setCode(codeableConcept);
        restTemplate.postForEntity(DIAGNOSIS_REPORT_SERVICE, diagnosticReport, DiagnosticReport.class);

    }
}
