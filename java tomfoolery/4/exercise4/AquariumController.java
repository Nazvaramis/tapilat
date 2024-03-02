package aut.isp.lab4.exercise4;

import aut.isp.lab4.exercise4.FishFeeder;
import aut.isp.lab4.exercise4.LightControl;

public class AquariumController {

    String manufacturer;
    String model;
    float currentTime;

    float feedingTime;
    FishFeeder feeder;
    public LightControl lightControl;

    private static AquariumController aq1;
    AquariumController(String manufacturer, String model, float currentTime, FishFeeder feeder,LightControl lightControl){

        this.manufacturer=manufacturer;
        this.model=model;
        this.currentTime=currentTime;
        this.feeder=feeder;
        this.lightControl=lightControl;

    }


    public void  setCurrentTime(float currentTime) {

        this.currentTime=currentTime;
        if(this.currentTime == this.feedingTime) this.feeder.feed();

    }

    public void setFeedingTime(float feedingTime){

        this.feedingTime=feedingTime;

    }
    public float getCurrentTime(){return this.currentTime;};


    public String toString(){

        return "An aquarium made by " + manufacturer + " with the model " + model + " and the current time is " + currentTime;
    }

    public static AquariumController getInstance(String manufacturer, String model, float currentTime, FishFeeder feeder, LightControl lightControl){
        if(aq1==null){
            aq1= new AquariumController(manufacturer,model,currentTime,feeder,lightControl);
        }

        return aq1;
    }

}
