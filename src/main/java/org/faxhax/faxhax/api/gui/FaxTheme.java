package org.faxhax.faxhax.api.gui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.mc12.GLInterface;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.util.render.FaxColor;
import org.faxhax.faxhax.client.modules.client.FaxClickGUI;
import org.faxhax.faxhax.client.modules.client.FaxColors;

import java.awt.*;

public class FaxTheme extends GameSenseTheme {

    public FaxTheme(ColorScheme scheme, int height, int border) {
        super(scheme, height, border, 0);
        panelRenderer=new FaxRenderer(0,height,border,0);
        containerRenderer=new FaxRenderer(1,height-2,border,0);
        componentRenderer=new FaxRenderer(2,height-2,border,0);
    }


    protected class FaxRenderer extends GameSenseTheme.ComponentRenderer {

        public FaxRenderer(int level, int height, int border, int scroll) {
            super(level, height, border, scroll);
        }

        @Override
        public void renderRect(Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
            Color color = getMainColor(focus, active);
            context.getInterface().fillRect(rectangle, color, color, color, color);
            if (overlay) {
                Color overlayColor;
                if (context.isHovered()) {
                    overlayColor = new Color(255,255,255,100);
                } else {
                    overlayColor = new Color(255, 255, 255, 0);
                }
                context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
            }
            if(level == 0) {
                GLInterface.end();
                if(FaxClickGUI.customFont.getValue()) FaxHax.FONT.drawCenteredStringWithShadow(text,rectangle,new FaxColor(getFontColor(focus)));
                else drawCenteredString(text,rectangle,getFontColor(focus));
                GLInterface.begin();
            }
            else {
                Point stringPos = new Point(rectangle.x + 3, rectangle.y - 2);
                stringPos.translate(0, border);
                context.getInterface().drawString(stringPos, text, getFontColor(focus));
//                TODO: str = if(moduleOpened) "-" else "+"
//                if(level==1) context.getInterface().drawString(new Point(rectangle.width - 5, rectangle.y), str, getFontColor(focus));
            }
        }

        @Override
        public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
            Color color=getColorScheme().getOutlineColor();
            if (level==0) {
                context.getInterface().fillRect(new Rectangle(context.getPos(),new Dimension(context.getSize().width,1)),color,color,color,color);
                context.getInterface().fillRect(new Rectangle(context.getPos(),new Dimension(1,context.getSize().height)),color,color,color,color);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x+context.getSize().width-1,context.getPos().y),new Dimension(1,context.getSize().height)),color,color,color,color);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x,context.getPos().y+context.getSize().height-1),new Dimension(context.getSize().width, 1)),color,color,color,color);
            }
        }

        @Override
        public Color getMainColor(boolean focus, boolean active) {
            Color color = FaxColors.categoryBgColor.getValue();
            if (level==0 && active) return new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
            return super.getMainColor(focus,active);
        }
    }

    public void drawCenteredString(String text, Rectangle rect, Color color) {
        GLInterface.end();
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        int width = font.getStringWidth(text);
        int height = font.FONT_HEIGHT;
        int x = rect.x + (rect.width - width) / 2;
        int y = (rect.y + (rect.height - height) / 2) + 1;
        font.drawStringWithShadow(text,x,y,color.getRGB());
        GLInterface.begin();
    }
}
