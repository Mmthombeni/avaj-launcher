package aircrafts;

import weather.Coordinates;

public class Aircraft {
    protected String name;
    protected long id;
    protected Coordinates coordinates;
    private static long idCounter;

    protected Aircraft(String name, Coordinates coordinates){

    }

    private long nextId(){
        return ++idCounter;
    }
}
