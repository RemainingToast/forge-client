package org.faxhax.faxhax.client.modules.render;

import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.common.MinecraftForge;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

public class FaxCustomFov extends FaxModule {

    public static FaxSetting.Integer customfov;
    public static FaxSetting.Integer itemfov;

    public FaxCustomFov() {
        super("CustomFOV", FaxCategory.Render);
        setDrawn(false);
    }

    @Override
    public void setup() {
        customfov = registerInteger("FOV", 110, 0, 170);
        itemfov = registerInteger("Item FOV", 110, 0, 170);
    }

    private float fov;

    @Override
    protected void onEnable() {
        fov = mc.gameSettings.fovSetting;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onUpdate() {
        mc.gameSettings.fovSetting = customfov.getValue();
    }

    @Override
    protected void onDisable() {
        mc.gameSettings.fovSetting = fov;
        MinecraftForge.EVENT_BUS.unregister(this);

    }
}
