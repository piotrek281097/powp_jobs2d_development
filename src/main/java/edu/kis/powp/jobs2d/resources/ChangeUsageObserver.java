package edu.kis.powp.jobs2d.resources;

import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class ChangeUsageObserver implements Subscriber {

    private Logger logger;

    public ChangeUsageObserver() {
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void update() {
        logger.info("Remaining ink: " + ResourceClassSingleton.getInstance().getInk());
        logger.info("Remaining usage: " + ResourceClassSingleton.getInstance().getUsage());
    }

}
