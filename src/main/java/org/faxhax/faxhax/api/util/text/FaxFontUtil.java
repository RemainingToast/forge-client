package org.faxhax.faxhax.api.util.text;

import net.minecraft.client.Minecraft;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.util.render.FaxColor;

public class FaxFontUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static FaxFontRenderer FONT;

    public static float drawStringWithShadow(boolean customFont, String text, int x, int y, FaxColor color) {
        if(customFont) {
            return FONT.drawStringWithShadow(text, x, y, color);
        }
        else {
            return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
        }
    }

    public static int getStringWidth(boolean customFont, String string) {
        if (customFont) {
            return FONT.getStringWidth(string);
        }
        else {
            return mc.fontRenderer.getStringWidth(string);
        }
    }

    public static int getFontHeight(boolean customFont) {
        if (customFont) {
            return FONT.getHeight();
        } else {
            return mc.fontRenderer.FONT_HEIGHT;
        }
    }

    public static void setFont(FaxFontRenderer Font) {
        FONT = Font;
    }

    public static FaxFontRenderer getFont() {
        return FONT;
    }
}
