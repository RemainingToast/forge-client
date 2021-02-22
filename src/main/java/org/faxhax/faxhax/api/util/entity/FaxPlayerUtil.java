package org.faxhax.faxhax.api.util.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;

import java.text.DecimalFormat;

public class FaxPlayerUtil {
    
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static BlockPos getPosFloored() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }

    public static String getPlayerPosFormatted(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String currPos = TextFormatting.WHITE + decimalFormat.format(mc.player.posX) + TextFormatting.GRAY + ", "
                + TextFormatting.WHITE + decimalFormat.format(mc.player.posY) + TextFormatting.GRAY + ", "
                + TextFormatting.WHITE + decimalFormat.format(mc.player.posZ);
        String dimension;
        if(mc.player.world.provider.getDimensionType() == DimensionType.NETHER){
            dimension = TextFormatting.GRAY + " [" + TextFormatting.WHITE + decimalFormat.format(mc.player.posX * 8)
                    + TextFormatting.GRAY + ", " + TextFormatting.WHITE + decimalFormat.format(mc.player.posZ * 8) + TextFormatting.GRAY + "]";
        } else {
            dimension = TextFormatting.GRAY + " [" + TextFormatting.WHITE + decimalFormat.format(mc.player.posX / 8)
                    + TextFormatting.GRAY + ", " + TextFormatting.WHITE + decimalFormat.format(mc.player.posZ / 8) + TextFormatting.GRAY + "]";
        }
        return TextFormatting.GRAY + "XYZ " + currPos + dimension;
    }
    
    public enum FacingDirection {
        North,
        South,
        East,
        West,
    }

    public static FacingDirection getDirection() {
        switch (MathHelper.floor((double) (mc.player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7) {
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

    public static String getAxis(){
        switch (getDirection()){
            case North:
                return "-Z";
            case East:
                return "+X";
            case South:
                return "+Z";
            case West:
                return "-X";
        }
        return "null";
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