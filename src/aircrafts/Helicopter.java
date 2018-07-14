package aircrafts;

import interfaces.Flyable;
import simulator.Simulator;
import weather.Coordinates;
import weather.WeatherProvider;
import weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);
    }
    @Override
    public void updateConditions() {
        WeatherProvider callItWeather = WeatherProvider.getProvider();
        String weather = callItWeather.getCurrentWeather(this.coordinates);

        if (weather.equals("FOG")){
            this.coordinates = new Coordinates((this.coordinates.getLongitude() + 1), this.coordinates.getLatitude(), this.coordinates.getHeight());
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): There's a FOG - " + this.coordinates.getHeight());
        }
        else if(weather.equals("RAIN")){
            this.coordinates = new Coordinates((this.coordinates.getLongitude() + 5), this.coordinates.getLatitude(), this.coordinates.getHeight());
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): This RAIN makes me miss the pool - " + this.coordinates.getHeight());

        }
        else if(weather.equals("SUN")){
            this.coordinates = new Coordinates((this.coordinates.getLongitude() + 10), this.coordinates.getLatitude(), (this.coordinates.getHeight() + 2));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): The SUN is out, no need to hang! - " + this.coordinates.getHeight());

        }
        else {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 12));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): Need to land before my rotor freeze and I drop land! - " + this.coordinates.getHeight());

        }

        if (this.coordinates.getHeight() <= 0){
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): is landing." );
            this.weatherTower.unregister(this);
            Simulator.writeToFile.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writeToFile.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
