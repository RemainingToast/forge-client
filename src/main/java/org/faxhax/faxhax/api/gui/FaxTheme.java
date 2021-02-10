package org.faxhax.faxhax.api.gui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.GameSenseTheme;

import java.awt.*;

public class FaxTheme extends GameSenseTheme {

    public FaxTheme(ColorScheme scheme, int height, int border, int scroll) {
        super(scheme, height, border, scroll);
        this.scheme = scheme;
        panelRenderer=new FaxRenderer(0,height,border,scroll);
        containerRenderer=new FaxRenderer(1,height,border,scroll);
        componentRenderer=new FaxRenderer(2,height,border,scroll);
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
                    overlayColor = getColorScheme().getInactiveColor();
                } else {
                    overlayColor = new Color(255, 255, 255, 0);
                }
                context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
            }
            Point stringPos = new Point(rectangle.x + 5, rectangle.y);
            if(level==0){
                stringPos = new Point((int) rectangle.getCenterX() - 15, rectangle.y);
            }
            stringPos.translate(0, border);
            context.getInterface().drawString(stringPos, text, getFontColor(focus));
        }

        @Override
        public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
//            Color color=getDefaultColorScheme().getOutlineColor();
//            if (level==0) {
//                context.getInterface().fillRect(new Rectangle(context.getPos(),new Dimension(context.getSize().width,1)),color,color,color,color);
//                context.getInterface().fillRect(new Rectangle(context.getPos(),new Dimension(1,context.getSize().height)),color,color,color,color);
//                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x+context.getSize().width-1,context.getPos().y),new Dimension(1,context.getSize().height)),color,color,color,color);
//            }
//            if (level==0 || open) {
//                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x,context.getPos().y+context.getSize().height-1),new Dimension(context.getSize().width,1)),color,color,color,color);
//                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x,context.getPos().y+getHeight(open)-1),new Dimension(context.getSize().width,1)),color,color,color,color);
//            }
        }

        @Override
        public void renderBackground(Context context, boolean focus) {
            super.renderBackground(context, focus);
//            context.getInterface().drawImage(context.getRect(),0,false, context.getInterface().loadImage("faxmachine.png"));
        }
    }
}
