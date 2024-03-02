package aut.isp.lab4.exercise1;

public class AquariumController {

private String manufacturer;

private String model;

private float currentTime;


float setCurrentTime(float currentTime){

    this.currentTime = currentTime;

    return currentTime;
}

AquariumController(String manufacturer,String model){
    this.manufacturer = manufacturer;
    this.model = model;
    this.currentTime = 0.0f;

}

    public String toString(){

    return "Manufacturer" + manufacturer + "Model " + model + "Current Time: " + currentTime;


    }




}








/*
    private String manufacturer;
    private String model;
    private float currentTime;

    public AquariumController(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.currentTime = 0.0f;
    }

    public void setCursorTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public String toString() {
        return "Manufacturer: " + manufacturer + ", Model: " + model + ", Current Time: " + currentTime;
    }

 */