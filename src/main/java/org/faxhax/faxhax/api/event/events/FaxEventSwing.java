package org.faxhax.faxhax.api.event.events;

import net.minecraft.util.EnumHand;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxEventSwing extends FaxEvent {
    
    public EnumHand hand;

    public FaxEventSwing(EnumHand hand) {
        super();
        this.hand = hand;
    }

}