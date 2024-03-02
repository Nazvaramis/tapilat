package aut.isp.lab4.exercise4;
import aut.isp.lab4.exercise4.AquariumController;
import aut.isp.lab4.exercise4.FishFeeder;
import aut.isp.lab4.exercise4.LightControl;

public class Exercise4 {
    public static void main(String[] args) {

        LightControl lightControl = new LightControl();
        FishFeeder feeder = aut.isp.lab4.exercise4.FishFeeder.getInstance("Acme", "Feeder1", 5);
        AquariumController aqq = AquariumController.getInstance("HappyFish", "Baban", 12.30F, feeder, lightControl);

        //aqq.lightControl.turnLightOff();
        int startTime = 7;
        int stopTime = 14;
        aqq.lightControl.setLightSchedule(startTime, stopTime);
        aqq.currentTime = 7f;
        if (aqq.currentTime == startTime) aqq.lightControl.turnLightOn();
        while (lightControl.isLightOn) {
            if (aqq.currentTime == stopTime) aqq.lightControl.turnLightOff();
            aqq.currentTime++;
            System.out.println("Time passed : " + aqq.currentTime);
        }
    }
}
