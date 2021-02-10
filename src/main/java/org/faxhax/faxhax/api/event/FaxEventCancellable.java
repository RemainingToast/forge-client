package org.faxhax.faxhax.api.event;

import me.zero.alpine.fork.event.type.Cancellable;
import net.minecraft.client.Minecraft;


public class FaxEventCancellable extends Cancellable {
	private final Era era = Era.EVENT_PRE;
	private final float ticks;

	public FaxEventCancellable() {
		ticks = Minecraft.getMinecraft().getRenderPartialTicks();
	}

	public Era getEra() {
		return era;
	}

	public float getTicks() {
		return ticks;
	}

	public enum Era {
		EVENT_PRE,
		EVENT_PERI,
		EVENT_POST;
	}
}