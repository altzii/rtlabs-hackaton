package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtlabs.hackaton.repository.PatientRepository;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Iterable<?> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findByIdMyUnqualifiedId(String id) {
        Optional<Patient> optionalPatient = patientRepository.findByIdMyUnqualifiedId(id);
        return optionalPatient.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(String id, Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.findByIdMyUnqualifiedId(id);
        Patient update = optionalPatient.orElseThrow(IllegalArgumentException::new);
        update.setId(patient.getId());
        update.setName(patient.getName());
        update.setGender(patient.getGender());
        update.setBirthDate(patient.getBirthDate());
        return patientRepository.save(update);
    }

    @Override
    public void deleteByIdMyUnqualifiedId(String id) {
        patientRepository.deleteByIdMyUnqualifiedId(id);
    }
}
