package org.faxhax.faxhax.api.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class FaxEvent extends Event {

    Era ERA;

    public FaxEvent() {

    }

    public FaxEvent(Era era) {
        this.ERA = era;
    }

    public Era getERA() {
        return this.ERA;
    }

    public void setStage(Era era) {
        this.ERA = era;
        this.setCanceled(false);
    }

    public enum Era {
        PRE,
        POST
    }

}

