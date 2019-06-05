package edu.kis.powp.jobs2d.resources;

import edu.kis.powp.observer.Publisher;

import java.awt.Point;

public class ResourceClassSingleton {

    private static ResourceClassSingleton ourInstance = new ResourceClassSingleton();

    public static ResourceClassSingleton getInstance() {
        return ourInstance;
    }

    private Publisher changePublisher = new Publisher();

    private double ink = 10000;
    private double usage = 10000;

    private ResourceClassSingleton() {
        changePublisher.addSubscriber(new ResourcesUsageObserver());
    }

    public boolean decrementInk(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);
        if (ink - distance < 0) {
            System.out.println("Nie mozna rysowac brak ink");
            changePublisher.notifyObservers();
            return false;
        }
        this.ink -= distance;
        return true;
    }

    public boolean decrementUsage(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);

        if (usage - distance < 0) {
            System.out.println("Nie mozna rysowac brak uasge");
            return false;
        }
        this.usage -= distance;
        return true;
    }

    public double getInk() {
        return ink;
    }

    public void setInk(double ink) {
        this.ink = ink;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
