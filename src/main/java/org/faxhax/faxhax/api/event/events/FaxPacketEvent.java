package org.faxhax.faxhax.api.event.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.faxhax.faxhax.api.event.FaxEvent;

@Cancelable
public class FaxPacketEvent extends FaxEvent {

	Packet<?> packet;

	public FaxPacketEvent(Packet<?> packet, Era era) {
		super(era);
		this.packet = packet;
	}

	public Packet<?> getPacket(){
		return this.packet;
	}


	@Cancelable
	public static class Receive extends FaxPacketEvent {
		public Receive(Packet<?> packet, Era era) {
			super(packet, era);
		}
	}

	@Cancelable
	public static class Send extends FaxPacketEvent {
		public Send(Packet<?> packet, Era era) {
			super(packet, era);
		}
	}
}