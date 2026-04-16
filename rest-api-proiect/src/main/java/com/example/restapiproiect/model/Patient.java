package com.example.restapiproiect.model;



public class Patient {

    private int id;
    private String department;
    private String name;
    private int age;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Patient(){

    }

    public Patient(int id, String department, String name, int age, String status) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.age = age;
        this.status = status;

    }

    public Patient(String department, String name, int age, String status) {
        this.department = department;
        this.name = name;
        this.age = age;
        this.status = status;

    }




}
