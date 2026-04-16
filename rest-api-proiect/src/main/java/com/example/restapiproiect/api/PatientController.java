package com.example.restapiproiect.api;

import com.example.restapiproiect.service.PatientManager;
import com.example.restapiproiect.model.Patient;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.*;



@RestController
@RequestMapping("/api/patients")

public class PatientController {

    final private PatientManager patientManager;

    public PatientController(PatientManager patientManager) {
        this.patientManager = patientManager;
    }

    @GetMapping()
    public List<Patient> getAll() {

        return patientManager.showallpatinets();
    }

    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient){

        int age = patient.getAge();
        String name = patient.getName();
        String department = patient.getDepartment();
        String status = patient.getStatus();


        return patientManager.registerPatient(name,age,department,status);

    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable int id){

        return patientManager.deletepatient(id);
    }

}


















