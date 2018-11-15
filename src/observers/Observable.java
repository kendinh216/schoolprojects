package observers;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver (Observer observer){
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void notifyObserver(Item i){
        for (Observer observer : observers){
            observer.update(i);
        }
    }
}
