package com.anmol1032;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.awt.*;

public class Theme extends DefaultMetalTheme {
    public ColorUIResource getWindowTitleInactiveBackground() {
        return new ColorUIResource(Color.cyan);
    }

    public ColorUIResource getWindowTitleBackground() {
        return new ColorUIResource(Color.GREEN);
    }

    public ColorUIResource getPrimaryControlHighlight() {
        return new ColorUIResource(Color.red);
    }

    public ColorUIResource getPrimaryControlDarkShadow() {
        return new ColorUIResource(new Color(0x3BF6C9));
    }

    public ColorUIResource getPrimaryControl() {
        return new ColorUIResource(Color.magenta);
    }

    public ColorUIResource getControlHighlight() {
        return new ColorUIResource(Color.yellow);
    }

    public ColorUIResource getControlDarkShadow() {
        return new ColorUIResource(Color.CYAN);
    }

    public ColorUIResource getControl() {
        return new ColorUIResource(Color.black);
    }

    @Override
    public ColorUIResource getWindowBackground() {
        return new ColorUIResource(Color.black);
    }

    @Override
    protected ColorUIResource getWhite() {
        return new ColorUIResource(Main.fg);
    }

    @Override
    protected ColorUIResource getBlack() {
        return new ColorUIResource(Main.bg);
    }
}
