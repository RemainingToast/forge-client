package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxPlayerTravel extends FaxEvent {
    
    public float strafe;
    public float vertical;
    public float forward;

    public FaxPlayerTravel(float strafeFloat, float verticalFloat, float forwardFloat) {
        strafe = strafeFloat;
        vertical = verticalFloat;
        forward = forwardFloat;
    }

}