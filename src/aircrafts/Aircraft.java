package aircrafts;

import weather.Coordinates;

public class Aircraft {
    protected String name;
    protected long id;
    protected Coordinates coordinates;
    private static long idCounter;

    protected Aircraft(String name, Coordinates coordinates){
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId(){
        return ++idCounter;
    }
}
