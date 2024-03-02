package isp.lab4.exercise3;

public class TemperatureSensor extends Sensor {

    private double temperature;
    public TemperatureSensor(double temperature,String installLocation,String name){
        super(installLocation,name);
        this.temperature = temperature;

    }

public double getReading(){
        return temperature;

}



}
