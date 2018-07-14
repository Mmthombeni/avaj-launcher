package aircrafts;

import interfaces.Flyable;
import simulator.Simulator;
import weather.Coordinates;
import weather.WeatherProvider;
import weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        WeatherProvider callItWeather = WeatherProvider.getProvider();
        String weather = callItWeather.getCurrentWeather(this.coordinates);

        if (weather.equals("FOG")){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 3));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): Try not to inhale the FOG it's not laughing gas! - " + this.coordinates.getHeight());
        }
        else if(weather.equals("RAIN")){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 5));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): This RAIN will make us pop! - " + this.coordinates.getHeight());

        }
        else if(weather.equals("SUN")){
            this.coordinates = new Coordinates((this.coordinates.getLongitude() + 2), this.coordinates.getLatitude(), (this.coordinates.getHeight() + 4));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): The SUN is out, it going to be a good day to inhale laughing gas! - " + this.coordinates.getHeight());

        }
        else {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 15));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): Need to land before this balloon turns into a SNOW man! - " + this.coordinates.getHeight());

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
