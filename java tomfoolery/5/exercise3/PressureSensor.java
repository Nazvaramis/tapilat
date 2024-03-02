package isp.lab4.exercise3;

public class PressureSensor extends Sensor {

private double pressure;



public PressureSensor(double pressure , String installLocation, String name){

    super(installLocation,name);
    this.pressure = pressure;

}



    public double getReading(){
        return pressure;

    }

}