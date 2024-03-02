package aut.isp.lab4.exercise5;

public class LevelSensor extends  Sensor {

    private float value;
    public float getValue(){


        return this.value;
    }

    public void setValue(float value){

        this.value=value;

    }

    public String toString(){

        return this.getManufacturer() + this.model;
    }

}
