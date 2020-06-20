package observers;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void notifyObservers(String message){
        for (var observer: observers){
            observer.update(message);
        }
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
}
