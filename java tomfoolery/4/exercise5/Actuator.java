package aut.isp.lab4.exercise5;

public abstract class Actuator {

    private String manufacturer;
    private String model;

    private boolean actuatorStatus;

    public void turnOn(){

        actuatorStatus=true;
        System.out.println("Actuator is turned on! ");

    }

    public void turnOff(){

        actuatorStatus=false;
        System.out.println("Actuator is turned off! ");

    }

    public abstract  String toString();

    public void setManufacturer(String manufacturer){
        this.manufacturer=manufacturer;

    }

    public String getManufacturer(){

        return this.manufacturer;
    }

    public void setModel(String model){
        this.model=model;

    }

    public String getModel(){

        return this.model;
    }

}
