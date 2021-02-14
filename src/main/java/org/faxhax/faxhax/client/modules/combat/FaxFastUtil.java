package org.faxhax.faxhax.client.modules.combat;

import net.minecraft.init.Items;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

public class FaxFastUtil extends FaxModule {

    FaxSetting.Boolean fastPlace;
    FaxSetting.Boolean fastBreak;
    FaxSetting.Boolean fastCrystal;
    FaxSetting.Boolean fastExp;

    public FaxFastUtil() {
        super("Fast Util", FaxCategory.Combat);
    }

    @Override
    public void setup() {
        fastPlace = registerBoolean("Place",true);
        fastBreak = registerBoolean("Break", true);
        fastCrystal = registerBoolean("Crystals", true);
        fastExp = registerBoolean("EXP Bottles", true);
    }

    @Override
    public void onUpdate() {
        if(mc.player == null) return;
        boolean mainExp = mc.player.getHeldItemMainhand().getItem().equals(Items.EXPERIENCE_BOTTLE);
        boolean offExp = mc.player.getHeldItemOffhand().getItem().equals(Items.EXPERIENCE_BOTTLE);
        boolean mainCry = mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL);
        boolean offCry = mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL);

        if((mainExp || offExp) && fastExp.getValue()) mc.rightClickDelayTimer = 0;
        if((mainCry || offCry) && fastCrystal.getValue()) mc.rightClickDelayTimer = 0;
        if(!(mainExp || offExp || mainCry || offCry) && fastPlace.getValue()) mc.rightClickDelayTimer = 0;
        if(fastBreak.getValue()) mc.playerController.blockHitDelay = 0;
    }
}
