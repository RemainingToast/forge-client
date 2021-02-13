package org.faxhax.faxhax.mixins;

import com.lukflug.panelstudio.mc12.GLInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GuiMainMenu.class)
public class FaxMixinGuiMainMenu {

    @Mutable
    @Shadow @Final private static ResourceLocation MINECRAFT_TITLE_TEXTURES;

    @Mutable
    @Shadow @Final private static ResourceLocation SPLASH_TEXTS;

    @Shadow private String splashText;

    @Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci){
//        MINECRAFT_TITLE_TEXTURES = new ResourceLocation("gui/faxhax.png");
//        SPLASH_TEXTS = new ResourceLocation("texts/splashes.txt");
//        Minecraft mc = Minecraft.getMinecraft();
//        GlStateManager.pushMatrix();
//        GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
//        GlStateManager.rotate(0.0F, 0.0F, 0.0F, 0.0F);
//        float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
//        f = f * 100.0F / (float)(mc.fontRenderer.getStringWidth(this.splashText) + 32);
//        GlStateManager.scale(f, f, f);
//        drawCenteredString(mc.fontRenderer, this.splashText, 0, -8, -256);
//        GlStateManager.popMatrix();
    }

    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, (float)(x - fontRendererIn.getStringWidth(text) / 2), (float)y, color);
    }
}
