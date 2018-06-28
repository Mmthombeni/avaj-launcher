package aircrafts;

import interfaces.Flyable;
import weather.Coordinates;
import weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates){
        super(name,coordinates);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {

    }
}
