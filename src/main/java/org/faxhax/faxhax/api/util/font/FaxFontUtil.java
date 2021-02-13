package org.faxhax.faxhax.api.util.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.util.FaxColor;

public class FaxFontUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static float drawStringWithShadow(boolean customFont, String text, int x, int y, FaxColor color) {
        if(customFont) {
            return FaxHax.FONT.drawStringWithShadow(text, x, y, color);
        }
        else {
            return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
        }
    }

    public static int getStringWidth(boolean customFont, String string) {
        if (customFont) {
            return FaxHax.FONT.getStringWidth(string);
        }
        else {
            return mc.fontRenderer.getStringWidth(string);
        }
    }

    public static int getFontHeight(boolean customFont) {
        if (customFont) {
            return FaxHax.FONT.getHeight();
        } else {
            return mc.fontRenderer.FONT_HEIGHT;
        }
    }
}
