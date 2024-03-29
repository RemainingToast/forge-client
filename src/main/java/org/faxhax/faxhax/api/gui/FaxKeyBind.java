package org.faxhax.faxhax.api.gui;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.settings.KeybindComponent;
import com.lukflug.panelstudio.settings.KeybindSetting;
import com.lukflug.panelstudio.theme.Renderer;

public class FaxKeyBind extends KeybindComponent {
    public FaxKeyBind (Renderer renderer, KeybindSetting keybind) {
        super(renderer, keybind);
    }

    @Override
    public void handleKey (Context context, int scancode) {
        context.setHeight(renderer.getHeight(false));
        if (hasFocus(context) && scancode==Keyboard.KEY_DELETE) {
            keybind.setKey(0);
            releaseFocus();
            return;
        }
        super.handleKey(context,scancode);
    }
}

