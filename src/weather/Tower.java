package weather;

import aircrafts.Aircraft;
import interfaces.Flyable;

import java.util.ArrayList;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> unregister = new ArrayList<>();

    public void register(Flyable flyable){
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        this.unregister.add(flyable);
    }

    protected void conditionsChanged(){
        for ( Flyable flyable : this.observers) {
            flyable.updateConditions();
        }
        for ( Flyable flyable: this.unregister) {
            if (this.observers.contains(flyable)){
                this.observers.remove(flyable);
            }
        }
    }
}