package org.faxhax.faxhax.api.event.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxEventDamageBlock extends FaxEventCancellable {

    private BlockPos BlockPos;
    private EnumFacing Direction;

    public FaxEventDamageBlock(BlockPos posBlock, EnumFacing directionFacing)
    {
        BlockPos = posBlock;
        setDirection(directionFacing);
    }

    public BlockPos getPos()
    {
        return BlockPos;
    }

    /**
     * @return the direction
     */
    public EnumFacing getDirection()
    {
        return Direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(EnumFacing direction)
    {
        Direction = direction;
    }
    
}