package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.shape.line.AbstractLine;

import java.awt.*;

public class CustomizableLine extends AbstractLine {

    public CustomizableLine() {
        super();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public void setDotted(boolean isDotted) {
        this.dotted = isDotted;
    }
}
