package fr.istic.mgrc.minieditor;

public interface Observable {

    public void notifyObservers();

    public void registerObserver(Observer observer);

    public void unregisterObserver(Observer observer);
}
