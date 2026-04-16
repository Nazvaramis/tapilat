package com.example.restapiproiect.service;
import com.example.restapiproiect.model.*;
import com.example.restapiproiect.repo.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientManager {

    private PatientRepository database;

    public PatientManager(PatientRepository database){
        this.database = database;
    }

    //PatientRepository database  = new DataBaseCaller();

    // probabil un switch cu descriere unde 1 face register , 2 face update , 3 .... etc ptr toate functiile
    public Patient registerPatient(String name ,int age , String Department , String status ){

        Patient p = new Patient(Department,name,age,status);
        int id = database.create(p);
        p.setId(id);

        //System.out.println("Patient created: " + p);

        return p;

    }


    public void updateDepartment(int id ,String newDepartment){
        database.updateDepartment(id,newDepartment);
    }

    // si aici presupun ca ID o sa il iea de la o interfata web din casuta aia care o sa il aduca in functia asta cu
    // REST API , ca basically rest api e curieru intre web si backend parca
    public Patient findpatientID(int id){
        return database.findById(id);
    }

    public List<Patient> showallpatinets(){

        return database.findAll();

    }

    public boolean deletepatient(int id){

        return database.delete(id);
        // aici facem , if true >> operation succesfull, else operation failed

    }

    public boolean updateStatus(int id,String newStatus){

        return database.updateStatus(id,newStatus);

    }





}



