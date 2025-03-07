package PolitGame.TheFanky;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReligionExpansion extends PlaceholderExpansion{
    @Override
    public @NotNull String getIdentifier() {
        return "religions";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CaptainFanky";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null || !player.isOnline()) {
            return "";
        }
        if(params.equalsIgnoreCase("religion")){
            try {
                String religion = CF_ReligionPlugin.getReligionNameFromPlayer((Player) player);
                if(religion != null) {
                    return religion;
                } else {
                    return "";
                }
            } catch (NumberFormatException e) {
                return "";
            }
        }
        return null;
    }
}
