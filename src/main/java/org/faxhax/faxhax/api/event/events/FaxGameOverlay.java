package org.faxhax.faxhax.api.event.events;

import net.minecraft.client.gui.ScaledResolution;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxGameOverlay extends FaxEventCancellable {

    public float partialTicks;
    private ScaledResolution scaledResolution;

    public FaxGameOverlay(float ticks, ScaledResolution res) {
        
        this.partialTicks = ticks;
        this.scaledResolution = res;

    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }
    
}