package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxPlayerTravel extends FaxEventCancellable {
    
    public float strafe;
    public float vertical;
    public float forward;

    public FaxPlayerTravel(float strafeFloat, float verticalFloat, float forwardFloat) {
        strafe = strafeFloat;
        vertical = verticalFloat;
        forward = forwardFloat;
    }

}