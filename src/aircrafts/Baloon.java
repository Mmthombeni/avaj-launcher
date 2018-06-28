package aircrafts;

import interfaces.Flyable;
import weather.Coordinates;
import weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
