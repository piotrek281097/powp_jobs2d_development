package edu.kis.powp.jobs2d.resources;

import edu.kis.powp.observer.Subscriber;

import javax.swing.*;

public class ResourcesUsageObserver implements Subscriber {

    @Override
    public void update() {
        System.out.println("Nasz update");
        JOptionPane.showMessageDialog(null, "Not enough resources to continue drawing");
    }

}
