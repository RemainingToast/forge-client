package org.faxhax.faxhax.api.util.text;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaxMessageUtil {
	private final static Minecraft mc = Minecraft.getMinecraft();
	private static final ChatFormatting RESET = ChatFormatting.RESET;
	private static final ChatFormatting GRAY = ChatFormatting.GRAY;
	private static final ChatFormatting DARK_GRAY = ChatFormatting.DARK_GRAY;
	private static final String PREFIX = DARK_GRAY + "[" + ChatFormatting.DARK_AQUA + FaxHax.MOD_NAME + DARK_GRAY + "] " + GRAY;

	public static void toggleMessage(FaxModule module) {
		String message;
		if (module.getName().equalsIgnoreCase("Crystal Aura")) {
			if(module.isOn()) message = PREFIX + "Started " + ChatFormatting.GREEN + "gamer chair " + GRAY + "mode";
			else message = PREFIX + "Stopped " + ChatFormatting.RED + "gamer chair " + GRAY + "mode";
		} else {
			if(module.isOn()) message = PREFIX + module.getName() + ChatFormatting.GREEN + " enabled";
			else message = PREFIX + module.getName() + ChatFormatting.RED + " disabled";
		}
		if (mc.player != null) {
			final ITextComponent itc = new TextComponentString(message).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(message))));
			mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 5936);
		}
	}

	public static void sendClientMessage(String message) {
		if (mc.player != null) {
			mc.player.sendMessage(new ChatMessage(message));
		}
	}

	public static class ChatMessage extends TextComponentBase {
		String message_input;

		public ChatMessage(String message) {
			Pattern p       = Pattern.compile("&[0123456789abcdefrlosmk]");
			Matcher m       = p.matcher(message);
			StringBuffer sb = new StringBuffer();

			while (m.find()) {
				String replacement = "\u00A7" + m.group().substring(1);
				m.appendReplacement(sb, replacement);
			}

			m.appendTail(sb);
			this.message_input = sb.toString();
		}

		public String getUnformattedComponentText() {
			return this.message_input;
		}

		@Override
		public ITextComponent createCopy() {
			return new ChatMessage(this.message_input);
		}
	}

	public static class EzMessages {

		private static String message;

		public static void setMessage(String message) {
			EzMessages.message = message;
		}

		public static String getMessage() {
			return message;
		}

	}

}

