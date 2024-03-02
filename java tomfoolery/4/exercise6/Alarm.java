package aut.isp.lab4.exercise6;

public class Alarm extends Actuator {

    private boolean AlarmStatus;

    public void turnOn(){

        AlarmStatus=true;
        System.out.println("Not enough water! ");
        System.out.println("Alarm is turned on !");

    }

    public void turnOff(){

        AlarmStatus=false;
        System.out.println("Alarm is turned off !");

    }

    public String toString(){

        return this.getManufacturer() + this.getModel();
    }

}
