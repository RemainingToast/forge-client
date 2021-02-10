package org.faxhax.faxhax.api.event;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.faxhax.faxhax.FaxHax;
import org.lwjgl.input.Keyboard;

public class FaxEventManager {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            FaxHax.MODULES.onBind(Keyboard.getEventKey());
        }
    }

}
