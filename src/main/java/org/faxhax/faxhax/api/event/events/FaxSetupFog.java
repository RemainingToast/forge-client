package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxSetupFog extends FaxEventCancellable {
    
    public int startCoords;
    public float partialTicks;

    public FaxSetupFog(int coords, float ticks) {
        startCoords = coords;
        partialTicks = ticks;
    }

}