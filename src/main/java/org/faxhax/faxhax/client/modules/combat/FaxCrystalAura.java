package org.faxhax.faxhax.client.modules.combat;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.BlockPos;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.math.FaxRotationUtil;

import java.util.Comparator;

public class FaxCrystalAura extends FaxModule {

    FaxSetting.Double breakRange;
    FaxSetting.Boolean antiSuicide;
    FaxSetting.Integer minSuicideHealth;
    FaxSetting.Boolean rotate;

    public FaxCrystalAura() {
        super("CrystalAura", FaxCategory.Combat);
    }

    @Override
    public void setup() {
        breakRange = registerDouble("BreakRange",4.4,0.0,10.0);
        antiSuicide = registerBoolean("AntiSuicide", true);
        minSuicideHealth = registerInteger("MinHealth", 15,1,36);
        rotate = registerBoolean("Rotate", true);
    }

    @Override
    public void onUpdate() {
        if(mc.player == null || mc.world == null || mc.player.isDead || antiSuicide.getValue()
                && (mc.player.getHealth() + mc.player.getAbsorptionAmount()) <= minSuicideHealth.getValue()) return;

        EntityEnderCrystal crystal = mc.world.loadedEntityList.stream()
                .filter(entity -> entity instanceof EntityEnderCrystal)
                .filter(e -> mc.player.getDistance(e) <= breakRange.getValue())
                .filter(e -> crystalCheck((EntityEnderCrystal) e))
                .map(entity -> (EntityEnderCrystal) entity)
                .min(Comparator.comparing(c -> mc.player.getDistance(c)))
                .orElse(null);

        if(crystal==null) return;

//        System.out.println("minecraft:end_crystal x: " + crystal.posX + " y: " + crystal.posY + " z: " + crystal.posZ);

        mc.playerController.attackEntity(mc.player,crystal);
        if(rotate.getValue()) {
            FaxRotationUtil.updateRotations();
            FaxRotationUtil.lookAtPos(new BlockPos(crystal.posX,crystal.posY,crystal.posZ));
            FaxRotationUtil.restoreRotations();
        }
    }

    public boolean crystalCheck(EntityEnderCrystal endCrystal){
        if(mc.player.canEntityBeSeen(endCrystal)) return true;
        return false;
    }
}
