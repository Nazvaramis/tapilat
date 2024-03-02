package isp.lab4.exercise2;


public class Laptop implements Chargeable {

    private int batteryLevel;

    public int durationMinutes;
    public int fullbattery = 100;
    public Laptop(int batteryLevel){

        this.batteryLevel = batteryLevel;


    }




    public int setbatterylevel(int batteryLevel){



        return batteryLevel;


    }

    public int getbatteryLevel(){

        System.out.println("The battery level is: "+ batteryLevel);
        return batteryLevel;
    }
/*
    public int setduration(int durationMinutes){

        // as putea face cu un for sa trec prin fiecare
        // % din baterie , tre sa stau sa ma gandesc

        for(i = 0 ; i < fullbattery ; i++){

        }

        if(durationMinutes <= 0 ) {
        batteryLevel = 100;
        durationMinutes = batteryLevel - fullbattery;
        }else if(durationMinutes <= 50){
            batteryLevel = 50;
            durationMinutes = batteryLevel - fullbattery;
        }

        this.durationMinutes = durationMinutes;


    }
    */
    public int charge(int durationMinutes){

        durationMinutes = fullbattery - batteryLevel;

        for(int i = batteryLevel; i < fullbattery;i++){


            System.out.println(durationMinutes + " minutes are left till full capacity");
            durationMinutes--;
            if(durationMinutes == 0) System.out.println("The battery has been fully charged , please unplug the phone to avoid battery overload.");
        }



        return durationMinutes;
    }
}
