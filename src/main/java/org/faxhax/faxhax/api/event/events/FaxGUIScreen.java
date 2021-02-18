package org.faxhax.faxhax.api.event.events;

import net.minecraft.client.gui.GuiScreen;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxGUIScreen extends FaxEvent {
	private final GuiScreen guiscreen;

	public FaxGUIScreen(GuiScreen screen) {
		super();

		guiscreen = screen;
	}

	public GuiScreen getScreen() {
		return guiscreen;
	}
}