package aut.isp.lab4.exercise2;

public class Fishfeeder {

   private String manufacturer;

   private String model;
   private int meals;


   public Fishfeeder(String manufacturer,String model, int meals){
       this.manufacturer = manufacturer;
       this.model = model;
       this.meals = meals;


   }

   public int fillUp(int fill){
       if(this.meals <= 0 ) {
           meals = meals + fill;
           System.out.println("You have filled the fish feeder, how many times you can feed " + meals);
           System.out.println("Fish dispenser has " +meals +" portions of food ");
       }
           return meals;

   }

   public int feed() {

       while (meals > 0) {
           System.out.println("The fish has been feed, current meals is " + meals);
           meals--;
       }
       if (meals <= 0){
           System.out.println(" \nThe fishfeeder is empty! restock as soon as possible \n ");
   }
       return meals;
   };


}
