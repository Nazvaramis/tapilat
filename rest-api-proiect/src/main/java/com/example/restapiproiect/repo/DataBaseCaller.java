package com.example.restapiproiect.repo;


import java.io.Serial;
import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;


import com.example.restapiproiect.config.DataBaseConfig;
import com.example.restapiproiect.model.Patient;
import org.springframework.stereotype.Repository;


@Repository
public class DataBaseCaller implements PatientRepository {



    public int create(Patient patient){
        String sql = "INSERT INTO patients (name, age, department, status) VALUES (?, ?, ?, ?)";
        try(Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,patient.getName());
            stmt.setInt(2,patient.getAge());
            stmt.setString(3,patient.getDepartment());
            stmt.setString(4,patient.getStatus());

            stmt.executeUpdate();

            try( ResultSet rs = stmt.getGeneratedKeys()){
                // daca-i un element nou adauagat , atuncea e true , si aici e If(true) daca o fo adauagat else false
                if(rs.next()){
                    return rs.getInt(1);

                }

            }


        }catch(SQLException e){
            e.printStackTrace();
            return -1;

        }
        return -1;
    }


    @Override
    public Patient findById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try(Connection conn = DataBaseConfig.getConnection(); PreparedStatement stwt = conn.prepareStatement(sql)){
            stwt.setInt(1,id);

            try(ResultSet rs = stwt.executeQuery()){
                if(rs.next()){
                    int Patientid = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String department = rs.getString("department");
                    String status = rs.getString("status");

                    // return object
                    return new Patient(Patientid,department,name,age,status);
                }else{

                    return null;
                }


            }

        }catch(SQLException e){
            System.out.println("Error ");
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean updateDepartment(int id, String newDepartment) {

        String sql = "UPDATE patients SET department = ? WHERE id = ?";
        try(Connection conn = DataBaseConfig.getConnection(); PreparedStatement stwt = conn.prepareStatement(sql);){
            stwt.setString(1,newDepartment);
            stwt.setInt(2,id);

            int rows = stwt.executeUpdate();
            if(rows > 0){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }



    }

    @Override
    public boolean delete(int id) {
        String sql =" DELETE FROM patients WHERE id = ?";
        try(Connection conn = DataBaseConfig.getConnection(); PreparedStatement stwt = conn.prepareStatement(sql);){
            stwt.setInt(1,id);
            int row = stwt.executeUpdate();

            if(row == 1){
                return true;
            }
            if(row == 0){
                return false;
            }
        }catch(SQLException e){

            e.printStackTrace();

            return false;
        }

        return false;

    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM patients";
        List<Patient> Patientlist = new ArrayList<>();
        try(Connection conn =DataBaseConfig.getConnection(); PreparedStatement stwt = conn.prepareStatement(sql);){



            ResultSet rs = stwt.executeQuery();
            while(rs.next()){
                int patientid = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String Department = rs.getString("department");
                String Status = rs.getString("status");


                Patient p = new Patient(patientid,Department,name,age,Status);

                Patientlist.add(p);


            }
            if(Patientlist.isEmpty()) {
                System.out.println("List is empty , no data succesfully extracted from database");
                return Patientlist;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return Patientlist;
    }

    @Override
    public boolean updateStatus(int id, String newStatus) {
        String sql = "UPDATE patients SET status = ? WHERE id = ?";
        try(Connection conn = DataBaseConfig.getConnection(); PreparedStatement stwt = conn.prepareStatement(sql)){
            stwt.setString(1,newStatus);
            stwt.setInt(2,id);

            int row =stwt.executeUpdate();

            if(row > 0 ){
                System.out.println(" successfully updated row");
                return true;
            }else{
                System.out.println(" Failed to update row");
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }




    }

}
