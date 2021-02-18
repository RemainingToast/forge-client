package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxSetupFog extends FaxEvent {
    
    public int startCoords;
    public float partialTicks;

    public FaxSetupFog(int coords, float ticks) {
        startCoords = coords;
        partialTicks = ticks;
    }

}