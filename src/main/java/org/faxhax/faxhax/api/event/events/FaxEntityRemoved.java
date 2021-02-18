package org.faxhax.faxhax.api.event.events;

import net.minecraft.entity.Entity;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxEntityRemoved extends FaxEvent {
    
    private final Entity entity;

    public FaxEntityRemoved(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }

}