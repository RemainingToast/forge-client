package org.faxhax.faxhax.api.event.events;

import net.minecraft.client.gui.ScaledResolution;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxGameOverlay extends FaxEvent {

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