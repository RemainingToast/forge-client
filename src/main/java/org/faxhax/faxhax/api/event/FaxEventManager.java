package org.faxhax.faxhax.api.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.faxhax.faxhax.FaxHax;
import org.lwjgl.input.Keyboard;

public class FaxEventManager {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player != null) FaxHax.MODULES.onUpdate();
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.isCanceled()) return;
    }

//    @SubscribeEvent
//    public void onRender(RenderGameOverlayEvent event) {
//        if (!event.isCanceled()) FaxHax.MODULES.onRender();
//    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            FaxHax.MODULES.onBind(Keyboard.getEventKey());
        }
    }

}
