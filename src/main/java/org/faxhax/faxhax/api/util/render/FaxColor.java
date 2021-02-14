package org.faxhax.faxhax.api.util.render;

import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class FaxColor extends Color {

    private static final long serialVersionUID = 1L;

    public FaxColor (int rgb) {
        super(rgb);
    }

    public FaxColor (int rgba, boolean hasalpha) {
        super(rgba,hasalpha);
    }

    public FaxColor (int r, int g, int b) {
        super(r,g,b);
    }

    public FaxColor (int r, int g, int b, int a) {
        super(r,g,b,a);
    }

    public FaxColor (Color color) {
        super(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
    }

    public FaxColor (FaxColor color, int a) {
        super(color.getRed(),color.getGreen(),color.getBlue(),a);
    }

    public static FaxColor fromHSB (float hue, float saturation, float brightness) {
        return new FaxColor(Color.getHSBColor(hue,saturation,brightness));
    }

    public float getHue() {
        return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[0];
    }

    public float getSaturation() {
        return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[1];
    }

    public float getBrightness() {
        return RGBtoHSB(getRed(),getGreen(),getBlue(),null)[2];
    }

    public void glColor() {
        GlStateManager.color(getRed()/255.0f,getGreen()/255.0f,getBlue()/255.0f,getAlpha()/255.0f);
    }
}
