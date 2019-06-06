package edu.kis.powp.jobs2d.resources;

import edu.kis.powp.observer.Subscriber;

import javax.swing.*;

public class WarningUsageObserver implements Subscriber {

    @Override
    public void update() {
        JOptionPane.showMessageDialog(null, "Not enough resources to continue drawing");
    }

}
