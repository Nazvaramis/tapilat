package aut.isp.lab4.exercise5;

public class FishFeeder {

    private static FishFeeder ff1;
    String manufacturer;
    String model;
    int meals;

    FishFeeder(String manufacturer, String model, int meals){

        this.manufacturer=manufacturer;
        this.model=model;
        this.meals=meals;

    }

    public static FishFeeder getInstance(String manufacturer, String model, int meals){
        if(ff1==null){
            if(meals<=14) ff1= new FishFeeder(manufacturer,model,meals);
            else System.out.println("Error! The meals introduced is bigger than our maximum meals capacity! ");
        }
        return ff1;
    }



    private void fillUp(){

        meals=14;
        System.out.println("Refill done, 14 meals available. ");

    }



    public void feed(){


        meals--;
        if(meals>0) System.out.println("There are " + meals + " meals avalaible. ");
        if(meals==0) System.out.println("There are no meals available! ");
        if(meals==0) {
            System.out.println("Plase refill! ");

        }
    }


    public String toString(){

        return manufacturer+ ", the model is "+ model+" and the nr of meals introduced is "+ meals;

    }



}
