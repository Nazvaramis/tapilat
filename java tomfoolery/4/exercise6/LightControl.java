package aut.isp.lab4.exercise6;

public class LightControl {

    private static int max_light_duration=8;
    private static int min_light_duration=6;

    public boolean isLightOn;

    public void turnLightOn() {
        isLightOn = true;
        System.out.println("Lights turned on.");
    }

    public void turnLightOff() {
        isLightOn = false;
        System.out.println("Lights turned off.");
    }


    public void setLightSchedule(int startTime, int endTime){


        if (startTime < 0 || startTime > 23 || endTime < 0 || endTime > 23) {
            System.out.println("Invalid start or end time.");
        }

        int duration=endTime-startTime;
        if(duration<min_light_duration || duration>max_light_duration) {

            System.out.println("Light duration should be between 6 and 8 hours! ");
        }
        else if(duration>min_light_duration && duration < max_light_duration) System.out.printf("Light Schedule set from %d to %d ! ",startTime,endTime);
    }

}
