package edu.kis.powp.jobs2d.resources;

import java.awt.*;

public class ResourceClassSingleton {

    private static ResourceClassSingleton ourInstance = new ResourceClassSingleton();

    public static ResourceClassSingleton getInstance() {
        return ourInstance;
    }

    private double ink = 10000;
    private double usage = 1000;
    private boolean isDotted = false;

    private ResourceClassSingleton() {
    }

    public void setDotted(boolean dotted) {
        isDotted = dotted;
    }

    public boolean decrementInk(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);
        if(ink - distance < 0) {
            System.out.println("Nie mozna rysowac");
            return false;
        }
        this.ink -= distance;
        return true;
        //        System.out.println(distance);
    }

    public void decrementUsage(int startPosX, int startPosY, int endPosX, int endPosY) {
        Point startingPoint = new Point(startPosX, startPosY);
        Point endingPoint = new Point(endPosX, endPosY);

        double distance = startingPoint.distance(endingPoint);
        this.usage -= distance;
        //        System.out.println(distance);
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
