package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxPlayerJump extends FaxEvent {
    
    public double motionX;
    public double motionY;

    public FaxPlayerJump(double x, double y) {
        super();

        this.motionX = x;
        this.motionY = y;
    }

}