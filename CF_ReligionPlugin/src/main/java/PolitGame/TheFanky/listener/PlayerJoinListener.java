package PolitGame.TheFanky.listener;

import PolitGame.TheFanky.CF_ReligionPlugin;
import PolitGame.TheFanky.ConfigData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        File playerData = ConfigData.getPlayerData();
        File config = ConfigData.getConfig();
        YamlConfiguration ymlPutPD = YamlConfiguration.loadConfiguration(playerData);
        YamlConfiguration ymlPutCFG = YamlConfiguration.loadConfiguration(config);
        String startReligion = ymlPutCFG.getString("Default-Religion");
        if(!ymlPutPD.contains("players." + player.getName())) {
            CF_ReligionPlugin.setReligionToPlayer(player, startReligion);
        }
    }
}
