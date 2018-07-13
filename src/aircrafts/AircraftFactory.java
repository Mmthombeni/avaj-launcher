package aircrafts;

import interfaces.Flyable;
import weather.Coordinates;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.equalsIgnoreCase("Baloon")){
            return new Baloon(name, coordinates);
        }
        if (type.equalsIgnoreCase("JetPlane")) {
            return new JetPlane(name, coordinates);
        }
        if (type.equalsIgnoreCase("Helicopter")){
            return new Helicopter(name, coordinates);
        }
        return null;
    }
}
