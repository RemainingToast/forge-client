package org.faxhax.faxhax.mixin.mixins;

import net.minecraft.client.Minecraft;
import org.faxhax.faxhax.FaxHax;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Redirect(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public void createDisplayPatch(String newTitle) {
        Display.setTitle(FaxHax.MOD_NAME + " v" + FaxHax.VERSION);
    }
}
