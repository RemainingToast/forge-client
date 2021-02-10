package org.faxhax.faxhax.api.event.events;

import net.minecraft.util.EnumHand;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxEventSwing extends FaxEventCancellable {
    
    public EnumHand hand;

    public FaxEventSwing(EnumHand hand) {
        super();
        this.hand = hand;
    }

}