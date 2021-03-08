package org.faxhax.faxhax.api.event;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.faxhax.faxhax.FaxHax;
import org.lwjgl.input.Keyboard;

import static org.faxhax.faxhax.FaxHax.MC;

public class FaxForgeEvents {

    public static FaxForgeEvents INSTANCE;

    public FaxForgeEvents() {
        INSTANCE = this;
    }

    public void init(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (MC.player != null) {
            FaxHax.MODULES.onUpdate();
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.isCanceled()) event.setCanceled(true);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
//        MinecraftForge.EVENT_BUS.post(event);
        if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            FaxHax.MODULES.onRender();
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            FaxHax.MODULES.onBind(Keyboard.getEventKey());
        }
    }

}
