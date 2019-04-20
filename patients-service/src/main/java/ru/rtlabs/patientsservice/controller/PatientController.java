package ru.rtlabs.patientsservice.controller;


import org.apache.catalina.LifecycleState;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rtlabs.patientsservice.repository.PatientRepository;

import java.util.Date;


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
        return repository.save((Patient) patient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Patient update(@PathVariable("id") String id, @RequestBody Patient patient) {

        Patient ss = new Patient();
        ss.setId("sdaSDSWDFSDG");
        ss.setBirthDate(new Date());
        Patient update = new Patient();
        update.setId(ss.getId());
        update.setName(ss.getName());
        update.setManagingOrganization(new Reference())
        update.setGender(ss.getGender());
        update.setBirthDate(ss.getBirthDate());
        logger.info(">>>>> Updating Patient:" + ss.getId() + ":" + ss.getName());
        return repository.save((Patient) update);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        logger.info(">>>>> Deleting Patient:" + id);
//        repository.delete(id);
    }

}
