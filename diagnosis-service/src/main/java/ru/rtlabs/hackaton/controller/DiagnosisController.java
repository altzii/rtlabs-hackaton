package ru.rtlabs.hackaton.controller;


import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rtlabs.hackaton.service.DiagnosisService;


@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/diagnostic")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping(produces = "application/json")
    public Iterable<?> findAll() {
        return diagnosisService.findAll();
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public DiagnosticReport findById(@PathVariable("id") String id) {
        return diagnosisService.findByIdMyUnqualifiedId(id);
    }

    @GetMapping(produces = "application/json", value = "/observation/{id}")
    public DiagnosticReport findByObservationId(@PathVariable("id") String id) {
        return diagnosisService.findByObservationId(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public DiagnosticReport create(@RequestBody DiagnosticReport diagnosticReport) {
        return diagnosisService.save(diagnosticReport);
    }

    @PutMapping(value = "/{id}")
    public DiagnosticReport update(@PathVariable("id") String id, @RequestBody DiagnosticReport diagnosticReport) {
        return diagnosisService.update(id, diagnosticReport);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        diagnosisService.deleteByIdMyUnqualifiedId(id);
    }

}
