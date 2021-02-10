package org.faxhax.faxhax.api.gui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.*;
import org.faxhax.faxhax.client.modules.client.FaxColors;

import java.awt.*;

public class FaxTheme extends ClearTheme {

    public FaxTheme(ColorScheme scheme, int height, int border) {
        super(scheme, false, height, border);
        this.scheme = scheme;
        panelRenderer=new FaxRenderer(true,height,border);
        componentRenderer=new FaxRenderer(false,height,border);
    }


    protected class FaxRenderer extends ClearTheme.ComponentRenderer {

        public FaxRenderer(boolean panel, int height, int border) {
            super(panel, height, border);
        }

        @Override
        public void renderTitle(Context context, String text, boolean focus, boolean active) {
            if (panel) super.renderTitle(context,text,focus,active);
            else {
                Color overlayColor;
                if (context.isHovered()) {
                    overlayColor=new Color(0,0,0,64);
                } else {
                    overlayColor=new Color(0,0,0,0);
                }
                context.getInterface().fillRect(context.getRect(),overlayColor,overlayColor,overlayColor,overlayColor);
                Color fontColor=getFontColor(focus);
                if (active) fontColor=getMainColor(focus,true);
                Point stringPos=new Point(context.getPos());
                stringPos.translate(0,getOffset());
                context.getInterface().drawString(stringPos,text,fontColor);
            }
        }

        @Override
        public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
            super.renderTitle(context, text, focus, active, open);
        }

        @Override
        public Color getMainColor (boolean focus, boolean active) {
            Color color = FaxColors.backgroundColor.getValue();
            if (panel && active) return new Color(color.getRed(),color.getGreen(),color.getBlue(),getColorScheme().getOpacity());
            return super.getMainColor(focus,active);
        }
    }
}
