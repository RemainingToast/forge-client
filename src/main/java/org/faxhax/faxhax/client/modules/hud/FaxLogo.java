package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import net.minecraft.util.ResourceLocation;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.util.render.FaxTextureHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class FaxLogo extends FaxHUDModule {

    ResourceLocation r = new ResourceLocation("gui/faxhax.png");

    public FaxLogo() {
        super("Logo", new Point(5,5));
    }

    @Override
    public void populate(Theme theme) {
        component = new FaxLogoComponent(theme);
    }

    private class FaxLogoComponent extends HUDComponent {

        public FaxLogoComponent(Theme theme) {
            super(getName(),theme.getPanelRenderer(),FaxLogo.this.position);
        }

        @Override
        public void render(Context context) {
            GL11.glPushMatrix();
            GL11.glTranslatef(context.getRect().x,context.getRect().y,0.0F);
            FaxTextureHelper.drawTexture(r,context.getRect().x,context.getRect().y,64,64);
            GL11.glPopMatrix();
        }

        @Override
        public int getWidth(Interface inter) {
            return 64;
        }

        @Override
        public void getHeight(Context context) {
            context.setHeight(64);
        }
    }
}
