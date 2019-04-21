package ru.rtlabs.hackaton.controller;


import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rtlabs.hackaton.service.PatientService;


@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(produces = "application/json")
    public Iterable<?> findAll() {
        return patientService.findAll();
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public Patient findById(@PathVariable("id") String id) {
        return patientService.findByIdMyUnqualifiedId(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Patient create(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping(value = "/{id}")
    public Patient update(@PathVariable("id") String id, @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        patientService.deleteByIdMyUnqualifiedId(id);
    }

}
