package ru.rtlabs.hackaton.controller;


import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.exceptions.FHIRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rtlabs.hackaton.service.SuperheroDetectionService;


@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/superhero")
public class SuperheroDetectionController {

    private final SuperheroDetectionService superheroDetectionService;

    @Autowired
    public SuperheroDetectionController(SuperheroDetectionService superheroDetectionService) {
        this.superheroDetectionService = superheroDetectionService;
    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void superHeroDetectionAndUpdate(@RequestBody Observation observation) throws FHIRException {
        superheroDetectionService.superHeroDetectionAndUpdate(observation);
    }

}
