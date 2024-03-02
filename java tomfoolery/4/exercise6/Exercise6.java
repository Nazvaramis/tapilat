package aut.isp.lab4.exercise6;

public class Exercise6 {
    public static void main(String[] args) {

        LightControl lightControl = new LightControl();
        LevelSensor lvl1=new LevelSensor();
        TemperatureSensor ts1=new TemperatureSensor();
        Alarm alarm1=new Alarm();
        Heater heater=new Heater();
        pHSensor pHSensor=new pHSensor();
        FishFeeder feeder = aut.isp.lab4.exercise6.FishFeeder.getInstance("Acme", "Feeder1", 5);


        aut.isp.lab4.exercise6.AquariumController aqq = AquariumController.getInstance("HappyFish", "Baban", 12.30F, feeder, lightControl,lvl1,ts1,alarm1,heater,pHSensor);
/*
        lvl1.setValue(12.20f);
        lvl1.setManufacturer("WaterSensor");
        lvl1.model="1200";
        //System.out.println(lvl1.getManufacturer());
        System.out.println(lvl1.toString());
        lvl1.setValue(14.20f);
        System.out.println("The level value is : "  + lvl1.getValue());
        alarm1.setManufacturer("SmartAlarm");
        alarm1.setModel("Pro");
        System.out.println(alarm1.toString());
        lvl1.setValue(12.3f);
        aqq.checkWaterLevel(lvl1);
        // water level grows
        lvl1.setValue(15f);
        aqq.checkWaterLevel(lvl1);
        ts1.setValue(14);
        aqq.checkTemperature(heater,ts1);
        ts1.setValue(27);
        aqq.checkTemperature(heater,ts1);
        */
        pHSensor.setValue(6.78f);
        aqq.checkpH(pHSensor);  // checks the ph value and its too low so it adds a coral
        // a day passed
        aqq.checkpH(pHSensor);
        // anotjer day passed
        aqq.checkpH(pHSensor);
        // now the level is between 7 and 8 , its normal but if we want to add some more corals we can add manually too
        // day passed
        aqq.addCorals(pHSensor);
        aqq.checkpH(pHSensor);

    }
}
