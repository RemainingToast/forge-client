package org.faxhax.faxhax.api.event.events;

import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxMotionUpdate extends FaxEventCancellable {

    public int stage;

    public FaxMotionUpdate(int stage) {
        super();
        this.stage = stage;
    }
    
}