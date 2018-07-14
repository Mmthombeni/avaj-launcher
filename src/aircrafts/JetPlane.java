package aircrafts;

import interfaces.Flyable;
import simulator.Simulator;
import weather.Coordinates;
import weather.WeatherProvider;
import weather.WeatherTower;

import java.awt.*;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates){
        super(name,coordinates);
    }

    @Override
    public void updateConditions() {
        WeatherProvider callItWeather = WeatherProvider.getProvider();
        String weather = callItWeather.getCurrentWeather(this.coordinates);

        if (weather.equals("FOG")){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), (this.coordinates.getLatitude() + 1), this.coordinates.getHeight());
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): How does one see in this FOG! - " + this.coordinates.getHeight());
        }
        else if(weather.equals("RAIN")){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), (this.coordinates.getLatitude() + 5), this.coordinates.getHeight());
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): This RAIN will give you the jetters! - " + this.coordinates.getHeight());

        }
        else if(weather.equals("SUN")){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), (this.coordinates.getLatitude() + 10), (this.coordinates.getHeight() + 2));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): The SUN is out, it jet-off time! - " + this.coordinates.getHeight());

        }
        else {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), (this.coordinates.getHeight() - 7));
            Simulator.writeToFile.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): Just don't jet SNOW! - " + this.coordinates.getHeight());

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
