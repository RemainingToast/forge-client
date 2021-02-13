package org.faxhax.faxhax.client.modules.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.GameType;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FaxFakePlayer extends FaxModule {

    FaxSetting.Mode player;
    FaxSetting.Boolean copyInv;
    FaxSetting.Boolean potionEffects;
    FaxSetting.Boolean maxArmour;

    public FaxFakePlayer() {
        super("FakePlayer", FaxCategory.Misc);
        setDrawn(true);
    }

    @Override
    public void setup() {
        player = registerMode("Player", Arrays.asList("FaxMachine5781", "RemainingToest", "NotTolon", "You"), "FaxMachine5781");
        copyInv = registerBoolean("Copy Inventory", true);
        potionEffects = registerBoolean("Potion Effects", true);
        maxArmour = registerBoolean("Max Armour", true);
    }

    private EntityOtherPlayerMP clonedPlayer;
    private int ENTITY_ID = -5781;

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

        if(copyInv.getValue()) clonedPlayer.inventory.copyInventory(mc.player.inventory);
        if(maxArmour.getValue()) addMaxArmour(clonedPlayer);
        if(potionEffects.getValue()) copyPotionEffects(mc.player, clonedPlayer);

        clonedPlayer.copyLocationAndAnglesFrom(mc.player);
        clonedPlayer.rotationYawHead = mc.player.rotationYawHead;

        mc.world.addEntityToWorld(ENTITY_ID, clonedPlayer);
        clonedPlayer.onLivingUpdate();
    }

    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntityFromWorld(ENTITY_ID);
        }
    }

    private void addMaxArmour(EntityPlayer player){
        InventoryPlayer inventory = player.inventory;
        inventory.armorInventory.set(3, addMaxEnchantments(new ItemStack(Items.DIAMOND_HELMET), Arrays.asList(
                Enchantments.PROTECTION,
                Enchantments.UNBREAKING,
                Enchantments.RESPIRATION,
                Enchantments.AQUA_AFFINITY,
                Enchantments.MENDING
        )));
        inventory.armorInventory.set(2, addMaxEnchantments(new ItemStack(Items.DIAMOND_CHESTPLATE), Arrays.asList(
                Enchantments.PROTECTION,
                Enchantments.UNBREAKING,
                Enchantments.MENDING
        )));
        inventory.armorInventory.set(1, addMaxEnchantments(new ItemStack(Items.DIAMOND_LEGGINGS), Arrays.asList(
                Enchantments.BLAST_PROTECTION,
                Enchantments.UNBREAKING,
                Enchantments.MENDING
        )));
        inventory.armorInventory.set(0, addMaxEnchantments(new ItemStack(Items.DIAMOND_BOOTS), Arrays.asList(
                Enchantments.PROTECTION,
                Enchantments.UNBREAKING,
                Enchantments.MENDING,
                Enchantments.FEATHER_FALLING,
                Enchantments.DEPTH_STRIDER
        )));
    }

    private ItemStack addMaxEnchantments(ItemStack item, List<Enchantment> enchantments){
        for(Enchantment e : enchantments){
            item.addEnchantment(e,e.getMaxLevel());
        }
        return item;
    }

    private void copyPotionEffects(EntityPlayer fromPlayer, EntityPlayer toPlayer){
        for(PotionEffect potionEffect : fromPlayer.getActivePotionEffects()){
            toPlayer.addPotionEffect(potionEffect);
            potionEffect.getPotion().applyAttributesModifiersToEntity(toPlayer,toPlayer.getAttributeMap(),potionEffect.getAmplifier());
        }
    }
}