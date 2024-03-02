package isp.lab4.exercise3;

public abstract class Sensor {

private String installLocation;
private String name;

public Sensor(String installLocation , String name){

    this.installLocation = installLocation;
    this.name = name;


}
    public abstract double getReading();

public String getInstallLocation(){

    return installLocation;
}
public String getName(){

    return name;
}





}

