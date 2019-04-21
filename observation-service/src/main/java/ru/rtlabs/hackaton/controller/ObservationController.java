package ru.rtlabs.hackaton.controller;


import org.hl7.fhir.dstu3.model.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.rtlabs.hackaton.service.ObservationService;

import java.util.UUID;


@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/observation")
public class ObservationController {

    private static final String SUPERHERO_DETECTION_SERVICE = "http://localhost:8080/superhero-detection-service/superhero";
    private final ObservationService observationService;
    private final RestTemplate restTemplate;

    @Autowired
    public ObservationController(ObservationService observationService, RestTemplate restTemplate) {
        this.observationService = observationService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(produces = "application/json")
    public Iterable<?> findAll(@RequestParam(value = "subject", required = false) String subject) {
        if (subject == null) {
            return observationService.findAll();
        } else {
            return observationService.findBySubject(subject);
        }
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public Observation findById(@PathVariable("id") String id) {
        return observationService.findOne(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Observation create(@RequestBody Observation observation) {
        restTemplate.postForEntity(SUPERHERO_DETECTION_SERVICE, observation, Observation.class);
        observation.setId(UUID.randomUUID().toString());
        return observationService.save(observation);
    }

    @PutMapping(value = "/{id}")
    public Observation update(@PathVariable("id") String id, @RequestBody Observation observation) {
        restTemplate.postForEntity(SUPERHERO_DETECTION_SERVICE, observation, Observation.class);
        return observationService.update(id, observation);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        observationService.deleteByIdMyUnqualifiedId(id);
    }

}
