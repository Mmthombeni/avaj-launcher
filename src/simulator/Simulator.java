package simulator;

import aircrafts.AircraftFactory;
import interfaces.Flyable;
import weather.WeatherTower;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();
    public static void main (String[] arg) throws InterruptedException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arg[0])); //creates buffered reader which will read
            String line = reader.readLine(); //assigns what was read to line
            if (line != null) {
                weatherTower = new WeatherTower(); //creating a new inst of weathertower
                line = line.trim();
                if (line == null){
                    System.out.println("No simulation");
                    System.exit(1);
                }
                String[] lineArray = line.split(" "); //delimiting using space
                int simulations = Integer.parseInt(lineArray[0]); //converting string to int and storing in simulation
                if (simulations < 0){
                    System.out.println("Invalid simulation count " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.isEmpty() != true){
                        lineArray = line.split(" ");

                        if (lineArray.length == 5) {
                            String type = lineArray[0];
                            String name = lineArray[1];
                            int longitude = Integer.parseInt(lineArray[2]);
                            int latitude = Integer.parseInt(lineArray[3]);
                            int height = Integer.parseInt(lineArray[4]);

                            Flyable flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
                            if (flyable != null && height > 0 ) {
                                flyables.add(flyable);
                            }
                        }
                    }
                }

                for (Flyable flyable : flyables ) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++){
                    System.out.println(i + ". Sim");   //remember to remove this line of code
                    weatherTower.changeWeather();
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't find file " + arg[0]);
        }
        catch (IOException e) {
            System.out.println("There was an error while reading the file" + arg[0]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Specify simulation file");
        }finally {
           // Logger.getLogger().close();
        }
    }

}
