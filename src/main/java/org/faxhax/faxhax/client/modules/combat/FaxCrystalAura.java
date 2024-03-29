package org.faxhax.faxhax.client.modules.combat;

import com.lukflug.panelstudio.mc12.GLInterface;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.math.FaxCrystalUtil;
import org.faxhax.faxhax.api.util.math.FaxRotationUtil;
import org.faxhax.faxhax.api.util.render.FaxRenderUtil;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;
import org.faxhax.faxhax.client.modules.misc.FaxAnnouncer;

import java.util.ArrayList;
import java.util.Comparator;

public class FaxCrystalAura extends FaxModule {

    FaxSetting.Double breakRange;
    FaxSetting.Double wallRange;
    FaxSetting.Mode handBreak;
    FaxSetting.Boolean antiSuicide;
    FaxSetting.Integer minSuicideHealth;
    FaxSetting.Boolean rotate;
    FaxSetting.Boolean announce;

    public FaxCrystalAura() {
        super("CrystalAura", FaxCategory.Combat);
    }

    @Override
    public void setup() {
        ArrayList<String> hands = new ArrayList<>();
        hands.add("Main");
        hands.add("Offhand");
        hands.add("Both");

        rotate = registerBoolean("Rotate", true);
        antiSuicide = registerBoolean("Anti Suicide", true);
        minSuicideHealth = registerInteger("Min Health", 15,1,36);
        handBreak = registerMode("Hand", hands, "Main");
        breakRange = registerDouble("Break Range",4.4,0.0,10.0);
        wallRange = registerDouble("Wall Range",4.4,0.0,10.0);
        announce = registerBoolean("Announce", true);
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&&(announce.getValue() || FaxAnnouncer.modules.getValue()&&FaxAnnouncer.INSTANCE.isOn())) FaxMessageUtil.toggleMessage(this);
    }

    @Override
    public void onUpdate() {
        if(mc.player == null || mc.world == null || mc.player.isDead || antiSuicide.getValue()
                && (mc.player.getHealth() + mc.player.getAbsorptionAmount()) <= minSuicideHealth.getValue()) return;

        EntityEnderCrystal crystal = mc.world.loadedEntityList.stream()
                .filter(entity -> entity instanceof EntityEnderCrystal)
                .filter(e -> mc.player.getDistance(e) <= breakRange.getValue())
                .map(entity -> (EntityEnderCrystal) entity)
                .min(Comparator.comparing(c -> mc.player.getDistance(c)))
                .orElse(null);

        if(crystal==null) return;

        if(!mc.player.canEntityBeSeen(crystal) && mc.player.getDistance(crystal) > wallRange.getValue()) return;

        breakCrystal(crystal);

        GLInterface.end();
        FaxRenderUtil.drawText(new BlockPos(crystal.posX,crystal.posY,crystal.posZ), String.valueOf(FaxCrystalUtil.calculateDamage(crystal, mc.player)));
        GLInterface.begin();
    }

    private void breakCrystal(EntityEnderCrystal endCrystal){
        if(rotate.getValue()) {
            FaxRotationUtil.updateRotations();
            FaxRotationUtil.lookAtPos(new BlockPos(endCrystal.posX,endCrystal.posY,endCrystal.posZ));
            FaxRotationUtil.restoreRotations();
        }

        mc.playerController.attackEntity(mc.player, endCrystal);

        swingArm();
    }

    private void swingArm(){
        if (handBreak.getValue().equalsIgnoreCase("Both")) {
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else if (handBreak.getValue().equalsIgnoreCase("Offhand")) {
            mc.player.swingArm(EnumHand.OFF_HAND);
        }
        else {
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

}
