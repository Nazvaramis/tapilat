package isp.lab4.exercise2;

public class SmartWatch implements Chargeable{



    private int batteryLevel;

    public int durationMinutes;
    public int fullbattery = 100;

    public SmartWatch(int batteryLevel){

        this.batteryLevel = batteryLevel;


    }
    public  int getbatteryLevel(){

        return batteryLevel;
    }

    public int charge(int durationInMinutes){

        durationMinutes = fullbattery - batteryLevel;

        for(int i = batteryLevel; i < fullbattery;i++){


            System.out.println(durationMinutes + " minutes are left till full capacity");
            durationMinutes--;
            if(durationMinutes == 0) System.out.println("The battery has been fully charged , please unplug the phone to avoid battery overload.");
        }



        return durationMinutes;
    }




}
