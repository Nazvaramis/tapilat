package aut.isp.lab4.exercise3;




public class Exercise3{

    public static void main(String args[]){

      Fishfeeder fisher = new Fishfeeder("idk ","idk^2",5);
     AquariumController idk = new AquariumController("Bosch","latest edition idk ", 13.50f,fisher);

     idk.check(13.50f);
     idk.setCurrentTime(13.50f);




    }



}
/*
public class Exercise3 {

    public static void main(String[] args) {
        FishFeeder feeder = new FishFeeder("Feeder 1", "Model 1");
        AquariumController aquarium = new AquariumController(feeder, "Manufacturer 1", "Model 1");

        aquarium.setFeedingTime(9.30f);
        aquarium.setCursorTime(9.25f);
        System.out.println(aquarium.toString());

        aquarium.setFeedingTime(13.45f);
        aquarium.setCursorTime(13.40f);
        System.out.println(aquarium.toString());

        aquarium.setFeedingTime(20.00f);
        aquarium.setCursorTime(19.55f);
        System.out.println(aquarium.toString());

        feeder.fillUp();
        System.out.println(feeder.toString());

        feeder.feed();
        System.out.println(feeder.toString());
    }
}

class AquariumController {
    private FishFeeder feeder;
    private String manufacturer;
    private String model;
    private float currentTime;
    private float feedingTime;

    public AquariumController(FishFeeder feeder, String manufacturer, String model) {
        this.feeder = feeder;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public void setCursorTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void setFeedingTime(float feedingTime) {
        this.feedingTime = feedingTime;
    }

    public String toString() {
        String output = "Aquarium Controller:\n";
        output += "Manufacturer: " + this.manufacturer + "\n";
        output += "Model: " + this.model + "\n";
        output += "Current Time: " + this.currentTime + "\n";
        output += "Feeding Time: " + this.feedingTime + "\n";
        return output;
    }
}

class FishFeeder {
    private String manufacturer;
    private String model;
    private int meals;

    public FishFeeder(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.meals = 14;
    }


    public void feed() {
        if (this.meals > 0) {
            System.out.println("Feeding the fish!");
            this.meals--;
        } else {
            System.out.println("The feeder is empty!");
        }
    }

    public void fillUp() {
        this.meals = 14;
        System.out.println("The feeder has been refilled with 14 meals!");
    }

    public String toString() {
        String output = "Fish Feeder:\n";
        output += "Manufacturer: " + this.manufacturer + "\n";
        output += "Model: " + this.model + "\n";
        output += "Meals: " + this.meals + "\n";
        return output;
    }
}


 */