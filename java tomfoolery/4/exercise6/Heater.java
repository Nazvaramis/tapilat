package aut.isp.lab4.exercise6;

public class Heater extends Actuator{

    private boolean HeaterStatus;

    public void turnOn(){

        HeaterStatus=true;
        System.out.println("The water is too cold! ");
        System.out.println("Heater is turned on !");

    }

    public void turnOff(){

        HeaterStatus=false;
        System.out.println("The water is good! ");
        System.out.println("Heater is turned off !");

    }

    public String toString(){

        return this.getManufacturer() + this.getModel();
    }




}
