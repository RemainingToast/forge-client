package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxMotionUpdate extends FaxEvent {

    public int stage;

    public FaxMotionUpdate(int stage) {
        super();
        this.stage = stage;
    }
    
}