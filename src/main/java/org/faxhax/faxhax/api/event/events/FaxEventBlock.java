package org.faxhax.faxhax.api.event.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxEventBlock extends FaxEvent {

    public BlockPos pos;
    public EnumFacing facing;

    private int stage;

    public FaxEventBlock(final int stage, final BlockPos pos, final EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
        this.stage = stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

}
