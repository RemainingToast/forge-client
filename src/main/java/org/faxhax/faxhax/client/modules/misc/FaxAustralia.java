package org.faxhax.faxhax.client.modules.misc;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.faxhax.faxhax.api.module.FaxModule;

public class FaxAustralia extends FaxModule {
    public FaxAustralia() {
        super("Australia", FaxCategory.Misc);
    }

    @Override
    public void onUpdate() {
        if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayerSP) {
            if (mc.entityRenderer.getShaderGroup() != null)
                mc.entityRenderer.getShaderGroup().deleteShaderGroup();

            try {
                mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/flip.json"));
            } catch (Exception ignored) {}
        } else if (mc.entityRenderer.getShaderGroup() != null && mc.currentScreen == null) {
            mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    @Override
    protected void onDisable() {
        mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }
}
