package aut.isp.lab4.exercise3;

public class AquariumController {

    Fishfeeder fish;
    private String manufacturer;

    private float feedingtime;
    private String model;

    private float currentTime;


    public float setCurrentTime(float currentTime){

        this.currentTime = currentTime;
        if(currentTime == feedingtime) {
            System.out.println("It's feeding time my kittens " );
            fish.feed();

            return currentTime;

        }else
            System.out.println("it's not feeding time lmao");
        return currentTime;


    }

    AquariumController(String manufacturer,String model,float currentTime,Fishfeeder fish){
        this.manufacturer = manufacturer;
        this.model = model;
        this.currentTime = currentTime;
        this.feedingtime = 13.50f;
        this.fish = fish;

    }


    public boolean check(float feedingtime){

        if(currentTime == feedingtime){
            System.out.println("It is wednesday my dudes");
            return true;
        }else return false;



    }
    public String toString(){

        return "Manufacturer" + manufacturer + "Model " + model + "Current Time: " + currentTime;


    }
    public float FeedingTime(){


        return feedingtime;

    }




}
