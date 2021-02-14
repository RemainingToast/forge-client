package org.faxhax.faxhax.api.util.render;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.faxhax.faxhax.api.util.entity.FaxEnemyUtil;
import org.faxhax.faxhax.api.util.entity.FaxFriendUtil;

public class FaxTabUtil {

    public static String get_player_name(final NetworkPlayerInfo info) {
        final String name = (info.getDisplayName() != null) ? info.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(info.getPlayerTeam(),
                info.getGameProfile().getName());
        if (FaxFriendUtil.isFriend(name)) {
            return section_sign() + "6" + name;
        }
        if (FaxEnemyUtil.isEnemy(name)) {
            return section_sign() + "c" + name;
        }
        return name;
    }

    public static String section_sign() {
        return "\u00A7";
    }

}
