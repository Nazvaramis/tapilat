package aut.isp.lab4.exercise2;


public class Exercise2 {




public static void main(String args[]){

   Fishfeeder baba = new Fishfeeder("oblitus es somnium","Rome",5);
    System.out.println("Check this shit out \n " + baba + "\n");
    baba.feed();

    baba.fillUp(5);
    baba.feed();

}


}



/*

private String manufacturer;
        private String model;
        private int meals;

        public Exercise2(String manufacturer, String model) {
            this.manufacturer = manufacturer;
            this.model = model;
            this.meals = 0;
        }

        public void feed() {
            if (meals > 0) {
                meals--;
                System.out.println("Feeding the fish. Meals left: " + meals);
            } else {
                System.out.println("No more meals left. Please fill up.");
            }
        }

        public void fillUp() {
            this.meals = 14;
            System.out.println("Fish feeder is now filled up. Meals: " + meals);
        }

        public String toString() {
            return "Manufacturer: " + manufacturer + ", Model: " + model + ", Meals: " + meals;
        }



        public static void main(String[] args) {
            Exercise2 fishFeeder = new Exercise2("ABC", "XYZ");
            System.out.println(fishFeeder.toString()); // Manufacturer: ABC, Model: XYZ, Meals: 0

            fishFeeder.fillUp(); // Fish feeder is now filled up. Meals: 14
            System.out.println(fishFeeder.toString()); // Manufacturer: ABC, Model: XYZ, Meals: 14

            fishFeeder.feed(); // Feeding the fish. Meals left: 13
            System.out.println(fishFeeder.toString()); // Manufacturer: ABC, Model: XYZ, Meals: 13
        }




 */




