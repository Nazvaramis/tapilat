package aut.isp.lab4.exercise5;


public class Exercise5 {
    public static void main(String[] args) {

        LightControl lightControl = new LightControl();
        LevelSensor lvl1=new LevelSensor();
        TemperatureSensor ts1=new TemperatureSensor();
        Alarm alarm1=new Alarm();
        Heater heater=new Heater();
        FishFeeder feeder = aut.isp.lab4.exercise5.FishFeeder.getInstance("Acme", "Feeder1", 5);


        aut.isp.lab4.exercise5.AquariumController aqq = AquariumController.getInstance("HappyFish", "Baban", 12.30F, feeder, lightControl,lvl1,ts1,alarm1,heater);

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
    }
}
