package isp.lab8.airways;

import com.fasterxml.jackson.databind.ObjectMapper;
import examples.files.FileReadUtil;
import examples.serializableJson.Vehicle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.SecureDirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise {
    public static void main(String[] args) throws IOException {
        String workingFolder = "C:\\routes";
        ObjectMapper objectMapper = new ObjectMapper();

        List<Route> routeList;
        boolean ok = true;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while (ok) {
            System.out.println("Welcome to the Airplane Route Management");
            System.out.println("Choose one of the following: ");
            System.out.println("1. Add a new route");
            System.out.println("2. Load the route and calculate distance");
            System.out.println("3. Delete a route");
            System.out.println("4. List the routes");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addRoute(sc2);
                    break;
                case 2:
                    loadRoutes(sc2);
                    break;
                case 3:
                    deleteRoute(sc2);
                    break;
                case 4:
                    listRoutes(sc2);
                    break;
                case 5:
                    ok = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        Waypoint LRCL = new Waypoint(1, "LRCL", 46.7852, 23.6862, 415);
        Waypoint TASOD = new Waypoint(2, "TASOD", 47.0548, 23.9212, 10460);
        Waypoint SOPAV = new Waypoint(3, "SOPAV", 46.9804, 24.7365, 10900);
        Waypoint BIRGU = new Waypoint(4, "BIRGU", 45.9467, 26.0217, 10200);
        Waypoint LROP = new Waypoint(5, "LROP", 44.5711, 26.0858, 106);

        System.out.println(WaypointDistanceCalculator.calculateDistance(LRCL.getLatitude(), LRCL.getLongitude(), LROP.getLatitude(), LROP.getLongitude()));
        objectMapper.writeValue(new FileWriter(workingFolder + "\\" + "LRCL.json"), LRCL);
        objectMapper.writeValue(new FileWriter(workingFolder + "\\" + "TASOD.json"), TASOD);
        objectMapper.writeValue(new FileWriter(workingFolder + "\\" + "SOPAV.json"), SOPAV);
        objectMapper.writeValue(new FileWriter(workingFolder + "\\" + "BIRGU.json"), BIRGU);
        objectMapper.writeValue(new FileWriter(workingFolder + "\\" + "LROP.json"), LROP);

        // with collection streams
        String jsonContent = FileReadUtil.readAllFileLines(workingFolder + "\\" + "LRCL.json").stream().collect(Collectors.joining("\n"));

        Waypoint lrcl = objectMapper.readValue(jsonContent, Waypoint.class);
        System.out.println("Waypoint read from file: " + lrcl);
    }

    public static void addRoute(Scanner sc) {
        System.out.println("Enter route name: ");
        String routeName = sc.nextLine();

        List<Waypoint> waypoints = new ArrayList<>();

        System.out.println("How many waypoints do you want?");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter waypoint name: ");
            String name = sc.next();

            System.out.println("Enter latitude:");
            double latitude = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter longitude:");
            double longitude = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter altitude:");
            int altitude = sc.nextInt();
            sc.nextLine();

            waypoints.add(new Waypoint(i, name, latitude, longitude, altitude));
        }

        Route route = new Route(routeName, waypoints);

        try {
            File routeDir = new File("c:\\Routes\\" + routeName);

            if (!routeDir.exists()) {
                routeDir.mkdir();
            }

            ObjectMapper objectMapper = new ObjectMapper();

            for (Waypoint waypoint : route.getWaypoints()) {
                objectMapper.writeValue(new File(routeDir.getPath() + "/" + waypoint.getName() + ".json"), waypoint);
            }

            System.out.println("Route created!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadRoutes(Scanner sc) {
        System.out.println("Enter route name:");
        String routeName = sc.next();
        try {
            File[] files = new File("C:\\Routes\\" + routeName).listFiles();

            ObjectMapper objectMapper = new ObjectMapper();
            List<Waypoint> waypoints = new ArrayList<>();

            for(File file : files){
                System.out.println("Waypoints for route " + routeName + ":");
                Waypoint waypoint = objectMapper.readValue(file, Waypoint.class);
                waypoints.add(waypoint);
            }
            for (Waypoint waypoint : waypoints) {
                System.out.println("Index: " + waypoint.getIndex());
                System.out.println("Name: " + waypoint.getName());
                System.out.println("Latitude: " + waypoint.getLatitude());
                System.out.println("Longitude: " + waypoint.getLongitude());
                System.out.println("Altitude: " + waypoint.getAltitude());
                System.out.println();
            }
            double distance = 0;
            for(int i = 0; i < waypoints.size()-1; i++){

                distance += calculateDistance(waypoints.get(i).getLatitude(),waypoints.get(i).getLongitude(),waypoints.get(i+1).getLatitude(),waypoints.get(i+1).getLongitude());
            }
            System.out.println("The distance is: " + distance);
        } catch (IOException e) {
            System.out.println("Error loading route " + routeName + ": " + e.getMessage());
        }
    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int earthRadius = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }

    public static void deleteRoute(Scanner sc) {
        System.out.println("Enter the route you want to delete: ");
        String routeName = sc.next();
        File[] files = new File("C:\\Routes\\" + routeName).listFiles();
        for(File file : files)
        {
            file.delete();
        }
        File dir = new File("C:\\Routes\\" + routeName);
        dir.delete();
    }

    public static void listRoutes(Scanner sc) {
        File directoryPath = new File("C:\\Routes");
        String contents[] = directoryPath.list();
        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
            System.out.println(contents[i]);
        }
    }
}
