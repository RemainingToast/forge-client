package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxPlayerJump extends FaxEventCancellable {
    
    public double motionX;
    public double motionY;

    public FaxPlayerJump(double x, double y) {
        super();

        this.motionX = x;
        this.motionY = y;
    }

}