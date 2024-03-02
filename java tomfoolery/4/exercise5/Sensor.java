package aut.isp.lab4.exercise5;

public abstract class Sensor {

    private String manufacturer;
    public String model;
    public String toString(){

        return this.manufacturer + this.model;
    }

    public void setManufacturer(String manufacturer){

        this.manufacturer=manufacturer;
    }

    public String getManufacturer(){
        return this.manufacturer;
    }
}
