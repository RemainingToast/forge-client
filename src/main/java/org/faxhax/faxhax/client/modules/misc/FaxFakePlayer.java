package org.faxhax.faxhax.client.modules.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.world.GameType;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.util.ArrayList;
import java.util.UUID;

public class FaxFakePlayer extends FaxModule {

    FaxSetting.Mode player;

    public FaxFakePlayer() {
        super("FakePlayer", FaxCategory.Misc);
        setDrawn(true);
    }

    @Override
    public void setup() {
        ArrayList<String> modes = new ArrayList<>();
        modes.add("FaxMachine5781");
        modes.add("RemainingToest");
        modes.add("NotTolon");
        modes.add("You");

        player = registerMode("Player", modes, "FaxMachine5781");
    }

    private EntityOtherPlayerMP clonedPlayer;

    public void onEnable() {
        if (mc.player == null || mc.player.isDead) {
            disable();
            return;
        }

        if(player.getValue().equalsIgnoreCase("FaxMachine5781")){
            clonedPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("6e7514e8-78a9-4cfd-80de-d400b97fece4"), "FaxMachine5781"));
        } else if (player.getValue().equalsIgnoreCase("RemainingToest")){
            clonedPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a68b9c6e-1606-4b0c-934f-198300d77d2b"), "RemainingToest"));
        } else if (player.getValue().equalsIgnoreCase("NotTolon")){
            clonedPlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a68b9c6e-1606-4b0c-934f-198300d77d2b"), "NotTolon"));
        } else if (player.getValue().equalsIgnoreCase("You")){
            clonedPlayer = new EntityOtherPlayerMP(mc.world, mc.getSession().getProfile());
        }

        clonedPlayer.inventory.copyInventory(mc.player.inventory);

        clonedPlayer.copyLocationAndAnglesFrom(mc.player);
        clonedPlayer.rotationYawHead = mc.player.rotationYawHead;

        mc.world.addEntityToWorld(-5781, clonedPlayer);
        clonedPlayer.onLivingUpdate();
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntityFromWorld(-5781);
        }
    }
}