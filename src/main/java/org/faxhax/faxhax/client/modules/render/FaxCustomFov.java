package org.faxhax.faxhax.client.modules.render;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

public class FaxCustomFov extends FaxModule {

    public static FaxSetting.Integer customFov;
    public static FaxSetting.Boolean itemBool;
    public static FaxSetting.Integer itemFov;

    public FaxCustomFov() {
        super("CustomFOV", FaxCategory.Render);
        setDrawn(true);
    }

    @Override
    public void setup() {
        customFov = registerInteger("FOV", 110, 0, 170);
        itemBool = registerBoolean("Items", true);
        itemFov = registerInteger("Item FOV", 110, 0, 170);
    }

    private float fov;

    @Override
    protected void onEnable() {
        fov = mc.gameSettings.fovSetting;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onUpdate() {
        mc.gameSettings.fovSetting = customFov.getValue();
    }

    @Override
    protected void onDisable() {
        mc.gameSettings.fovSetting = fov;
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onFovEvent(EntityViewRenderEvent.FOVModifier e) {
        if (itemBool.getValue()) e.setFOV(itemFov.getValue());
        mc.gameSettings.fovSetting = customFov.getValue();
    }
}
