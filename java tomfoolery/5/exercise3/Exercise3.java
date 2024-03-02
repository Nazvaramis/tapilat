package isp.lab4.exercise3;

public class Exercise3 {


    public static void main(String[] args) {
        TemperatureSensor tempSensor = new TemperatureSensor(25.5, "Room A", "Temp Sensor 1");
        PressureSensor pressureSensor = new PressureSensor(1000.0, "Room B", "Pressure Sensor 1");

        MonitoringService monitoringService = new MonitoringService();
        monitoringService.addSensor(tempSensor);
        monitoringService.addSensor(pressureSensor);

        System.out.println("Average temperature reading: " + monitoringService.getAverageTemperatureSensorReading());
        System.out.println("Average reading of all sensors: " + monitoringService.getAverageAllSensorReading());
    }





}
