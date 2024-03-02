package aut.isp.lab4.exercise6;

public class AquariumController {

    String manufacturer;
    String model;
    float currentTime;

    float feedingTime;
    FishFeeder feeder;
    LightControl lightControl;
    LevelSensor levelSensor;
    TemperatureSensor temperatureSensor;
    Heater heater;
    Alarm alarm;
    pHSensor pHSensor;

    private static AquariumController aq1;

    AquariumController(String manufacturer, String model, float currentTime, FishFeeder feeder, LightControl lightControl,LevelSensor levelSensor, TemperatureSensor temperatureSensor, Alarm alarm, Heater heater,pHSensor pHSensor){

        this.manufacturer=manufacturer;
        this.model=model;
        this.currentTime=currentTime;
        this.feeder=feeder;
        this.lightControl=lightControl;
        this.levelSensor=levelSensor;
        this.temperatureSensor=temperatureSensor;
        this.heater=heater;
        this.alarm=alarm;
        this.pHSensor=pHSensor;
    }


    public void  setCurrentTime(float currentTime) {

        this.currentTime=currentTime;
        if(this.currentTime == this.feedingTime) this.feeder.feed();

    }

    public void setFeedingTime(float feedingTime){

        this.feedingTime=feedingTime;

    }


    public String toString(){

        return "An aquarium made by " + manufacturer + " with the model " + model + " and the current time is " + currentTime;
    }

    public void checkTemperature(Heater heater, TemperatureSensor t1){

        if(t1.getValue()<24) heater.turnOn();
        if(t1.getValue()>=27) heater.turnOff();
    }


    public void checkWaterLevel(LevelSensor lvl){

        Alarm ala=new Alarm();
        if(lvl.getValue()<14)ala.turnOn();
        if(lvl.getValue()>=14)ala.turnOff();
    }

    public void addCorals(pHSensor pHSensor){

        if(pHSensor.getValue()<7)System.out.println("A coral is added becauase the pH it's too low! ");
        pHSensor.setValue((float) (pHSensor.getValue()+0.2));

    }

    public void addDriftWood(pHSensor pHSensor){

        if(pHSensor.getValue()>8) System.out.println("A DriftWood is added becauase the pH it's too high! ");

        pHSensor.setValue((float) (pHSensor.getValue()-0.2));
    }

    public void checkpH(pHSensor pHSensor){
        System.out.println("Current pH level is : "+ pHSensor.getValue());
        if(pHSensor.getValue()<7) addCorals(pHSensor);// trb sa adaugam treptat corali care cresc pH ul apei,adaugam o data pe zi si verificam de fiecare data , ne oprim cand e phul 8
        if(pHSensor.getValue()>8) addDriftWood(pHSensor);   // daca e prea mare phul bagam driftwood ca sa scada .
    }

    public static AquariumController getInstance(String manufacturer, String model, float currentTime, FishFeeder feeder, LightControl lightControl,LevelSensor levelSensor,TemperatureSensor temperatureSensor, Alarm alarm, Heater heater,pHSensor pHSensor){
        if(aq1==null){
            aq1= new AquariumController(manufacturer,model,currentTime,feeder,lightControl,levelSensor, temperatureSensor, alarm, heater,pHSensor);
        }

        return aq1;
    }


}
