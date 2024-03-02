package aut.isp.lab4.exercise1;

public class Exercise1 {


        public static void main(String[] args) {



             AquariumController controller = new AquariumController("Gigolo","Sugator de femei 6000");

            System.out.println(controller.toString());

             controller.setCurrentTime(13.40f);

            System.out.println(controller.toString());
        }


}
