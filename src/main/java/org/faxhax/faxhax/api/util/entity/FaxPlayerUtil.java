package org.faxhax.faxhax.api.util.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class FaxPlayerUtil {
    
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }
    
    public enum FacingDirection {
        North,
        South,
        East,
        West,
    }

    public static FacingDirection GetFacing() {
        switch (MathHelper.floor((double) (mc.player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7)
        {
            case 0:
            case 1:
                return FacingDirection.South;
            case 2:
            case 3:
                return FacingDirection.West;
            case 4:
            case 5:
                return FacingDirection.North;
            case 6:
            case 7:
                return FacingDirection.East;
        }
        return FacingDirection.North;
    }

    private static int totems = 0;

    public static int getTotemCount(){
        totems = 0;
        mc.player.inventory.mainInventory.stream()
                .filter(itemStack -> itemStack.item == Items.TOTEM_OF_UNDYING)
                .forEach(value -> totems++);
        mc.player.inventory.offHandInventory.stream()
                .filter(itemStack -> itemStack.item == Items.TOTEM_OF_UNDYING)
                .forEach(itemStack -> totems++);
        return totems;
    }

}