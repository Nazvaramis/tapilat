package com.example.restapiproiect.repo;

import java.util.List;
import com.example.restapiproiect.model.Patient;

public interface PatientRepository{

    //create

    int create(Patient patient);

    //search
    Patient findById(int id);

    //view all
    List<Patient> findAll();

    boolean updateStatus(int id, String newStatus);
    boolean updateDepartment(int id,String newDepartment);

    boolean delete(int id);

}
