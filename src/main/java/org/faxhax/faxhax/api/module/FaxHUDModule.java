package org.faxhax.faxhax.api.module;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.FaxHax;

import java.awt.*;

public abstract class FaxHUDModule extends FaxModule {

    protected FixedComponent component;
    protected Point position;

    public FaxHUDModule(String title, Point defaultPos) {
        super(title,FaxCategory.HUD);
        position = defaultPos;
    }

    public abstract void populate(Theme theme);

    public FixedComponent getComponent() {
        return component;
    }

    public void resetPosition() {
        component.setPosition(FaxHax.CLICKGUI.guiInterface,position);
    }

    public Point getPosition() {
        return position;
    }
}
