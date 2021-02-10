package org.faxhax.faxhax.api.event;


import net.minecraftforge.common.MinecraftForge;


public class FaxEventRegister {
//	public static void register_command_manager(FaxCommandManager manager) {
//		MinecraftForge.EVENT_BUS.register(manager);
//	}

	public static void registerEventManager(FaxEventManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}
}