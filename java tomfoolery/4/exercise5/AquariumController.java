package aut.isp.lab4.exercise5;

public class AquariumController {

    String manufacturer;
    String model;
    float currentTime;

    float feedingTime;
    FishFeeder feeder;
    LightControl lightControl; // should be sensor instead ( teams explanaition )
    LevelSensor levelSensor;
    TemperatureSensor temperatureSensor;
    Heater heater;
    Alarm alarm;

    private static AquariumController aq1;

    AquariumController(String manufacturer, String model, float currentTime, FishFeeder feeder, LightControl lightControl,LevelSensor levelSensor, TemperatureSensor temperatureSensor, Alarm alarm, Heater heater){

        this.manufacturer=manufacturer;
        this.model=model;
        this.currentTime=currentTime;
        this.feeder=feeder;
        this.lightControl=lightControl;
        this.levelSensor=levelSensor;
        this.temperatureSensor=temperatureSensor;
        this.heater=heater;
        this.alarm=alarm;
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

        if(lvl.getValue()<14)alarm.turnOn();
        if(lvl.getValue()>=14)alarm.turnOff();
    }

    public static AquariumController getInstance(String manufacturer, String model, float currentTime, FishFeeder feeder, LightControl lightControl,LevelSensor levelSensor,TemperatureSensor temperatureSensor, Alarm alarm, Heater heater){
        if(aq1==null){
            aq1= new AquariumController(manufacturer,model,currentTime,feeder,lightControl,levelSensor, temperatureSensor, alarm, heater);
        }

        return aq1;
    }


}
