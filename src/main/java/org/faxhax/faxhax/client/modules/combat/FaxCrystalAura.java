package org.faxhax.faxhax.client.modules.combat;

import net.minecraft.entity.item.EntityEnderCrystal;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.util.Comparator;

public class FaxCrystalAura extends FaxModule {

    FaxSetting.Double breakRange;
    FaxSetting.Boolean antiSuicide;
    FaxSetting.Integer minSuicideHealth;

    public FaxCrystalAura() {
        super("CrystalAura", FaxCategory.Combat);
    }

    @Override
    public void setup() {
        breakRange = registerDouble("BreakRange",4.4,0.0,10.0);
        antiSuicide = registerBoolean("AntiSuicide", true);
        minSuicideHealth = registerInteger("MinHealth", 15,1,36);
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

        System.out.println("minecraft:end_crystal x: " + crystal.posX + " y: " + crystal.posY + " z: " + crystal.posZ);
    }

    public boolean crystalCheck(EntityEnderCrystal endCrystal){

        return true;
    }
}
