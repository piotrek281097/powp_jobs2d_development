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
    private boolean isInk = true, isUsage = true;
    private boolean isWindowPopedUp = false;

    public void changeStatusIsWindowOpenedUp() {
        isWindowPopedUp = false;
    }

    public void changeResourcesStatus() {
        isInk = true;
        isUsage = true;
    }

    private ResourceClassSingleton() {
        changePublisher.addSubscriber(new WarningUsageObserver());
    }

    public boolean decrementInk(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);
        if (isInk && isUsage) {
            if (ink - distance < 0) {
                isInk = false;
                System.out.println("Nie mozna rysowac brak ink");
                changePublisher.notifyObservers();
                isWindowPopedUp = true;
                return false;
            }
        } else if(!isWindowPopedUp) {
            isWindowPopedUp = true;
            changePublisher.notifyObservers();
            return false;
        } else {
            return false;
        }
        this.ink -= distance;
        return true;
    }

    public boolean decrementUsage(int startPosX, int startPosY, int endPosX, int
            endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);

        if (isInk && isUsage) {
            if (usage - distance < 0) {
                isUsage = false;
                System.out.println("Nie mozna rysowac brak uasge");
                changePublisher.notifyObservers();
                isWindowPopedUp = true;
                return false;
            }
        } else if(!isWindowPopedUp) {
            isWindowPopedUp = true;
            changePublisher.notifyObservers();
            return false;
        } else {
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
