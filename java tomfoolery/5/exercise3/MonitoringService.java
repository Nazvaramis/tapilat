package isp.lab4.exercise3;
import java.util.*;

public class MonitoringService {


    private ArrayList<Sensor> sensors = new ArrayList<Sensor>();

    public void addSensor(Sensor sensor){
        sensors.add(sensor);
    }

    public double getAverageTemperatureSensorReading() {
        double sum = 0;
        int count = 0;
        for (Sensor sensor : sensors) {
            if (sensor instanceof TemperatureSensor) {
                sum += sensor.getReading();
                count++;
            }
        }
        return sum / count;
    }

    public double getAverageAllSensorReading() {
        double sum = 0;
        for (Sensor sensor : sensors) {
            sum += sensor.getReading();
        }
        return sum / sensors.size();
    }

}
