package org.faxhax.faxhax.client.modules.render;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;
import org.faxhax.faxhax.client.modules.misc.FaxAnnouncer;

import java.util.ArrayList;

public class FaxFullBright extends FaxModule {

    FaxSetting.Mode mode;


    public FaxFullBright() {
        super("FullBright", FaxCategory.Render);
        setDrawn(true);
        setHudInfo(mode.getValue());
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&& FaxAnnouncer.modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }

    @Override
    public void setup() {
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Gamma");
        modes.add("Potion");

        mode = registerMode("Mode", modes, "Gamma");
    }

    float oldgamma;

    @Override
    protected void onEnable() {
        oldgamma = mc.gameSettings.gammaSetting;
    }

    @Override
    public void onUpdate() {
        if (mode.getValue().equalsIgnoreCase("Gamma")) {
            mc.gameSettings.gammaSetting = 666f;
            mc.player.removePotionEffect(Potion.getPotionById(16));
        }
        else if (mode.getValue().equalsIgnoreCase("Potion")) {
            final PotionEffect potionEffect = new PotionEffect(Potion.getPotionById(16), 123456789, 5);
            potionEffect.setPotionDurationMax(true);
            mc.player.addPotionEffect(potionEffect);
        }
    }

    @Override
    protected void onDisable() {
        mc.gameSettings.gammaSetting = oldgamma;
        mc.player.removePotionEffect(Potion.getPotionById(16));
    }

}
