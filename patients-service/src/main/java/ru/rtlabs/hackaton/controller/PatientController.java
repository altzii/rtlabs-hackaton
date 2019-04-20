package ru.rtlabs.hackaton.controller;


import org.hl7.fhir.dstu3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rtlabs.hackaton.repository.PatientRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientRepository repository;
    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Iterable<?> findAll() {
        logger.info(">>>>>> Find All Patients");
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{id}")
    public Patient findById(@PathVariable("id") String id) {
        logger.info(">>>>>> Find Patient:" + id);
//         repository.findOne(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Patient create(@RequestBody Patient patient) {
        logger.info(">>>>> Creating Patient:" + patient.getId() + ":" + patient.getName());
        return repository.save(patient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Patient update(@PathVariable("id") String id, @RequestBody Patient patient) {


        Patient update = new Patient();
        update.setId("1");
        List<HumanName> names = new ArrayList<>();
        names.add(new HumanName().setText("Alexander"));
        update.setName(names);
        update.setManagingOrganization(new Reference());
        update.setGender(Enumerations.AdministrativeGender.MALE);
        update.setBirthDate(new Date());
        logger.info(">>>>> Updating Patient:" + update.getId());
        return repository.save(update);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        logger.info(">>>>> Deleting Patient:" + id);
//        repository.delete(id);
    }

}
