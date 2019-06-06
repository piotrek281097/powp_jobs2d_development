package edu.kis.powp.jobs2d.resources;

import edu.kis.powp.observer.Publisher;

import java.awt.Point;

public class ResourceClassSingleton {

    private static ResourceClassSingleton ourInstance = new ResourceClassSingleton();

    public static ResourceClassSingleton getInstance() {
        return ourInstance;
    }

    private Publisher warningPublisher = new Publisher();
    private Publisher changePublisher = new Publisher();

    private double ink = 10000;
    private double usage = 10000;
    private boolean isInk = true, isUsage = true;
    private boolean isWindowPopedUp = false;

    public void changeStatusIsWindowOpenedUp() {
        isWindowPopedUp = false;
    }


    private ResourceClassSingleton() {
        warningPublisher.addSubscriber(new WarningUsageObserver());
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }

    public boolean decrementInk(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);
        if (isInk && isUsage) {
            if (ink - distance < 0) {
                isInk = false;
                warningPublisher.notifyObservers();
                isWindowPopedUp = true;
                return false;
            }
        } else if(!isWindowPopedUp) {
            isWindowPopedUp = true;
            warningPublisher.notifyObservers();
            return false;
        } else {
            return false;
        }
        this.ink -= distance;
        changePublisher.notifyObservers();
        return true;
    }

    public boolean decrementUsage(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);

        if (isInk && isUsage) {
            if (usage - distance < 0) {
                isUsage = false;
                warningPublisher.notifyObservers();
                isWindowPopedUp = true;
                return false;
            }
        } else if(!isWindowPopedUp) {
            isWindowPopedUp = true;
            warningPublisher.notifyObservers();
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
