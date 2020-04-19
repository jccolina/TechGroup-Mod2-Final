package colinajose.model.notification;

public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    Object getState(Object object);
}
