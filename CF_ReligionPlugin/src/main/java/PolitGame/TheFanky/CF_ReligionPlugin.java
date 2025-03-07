package PolitGame.TheFanky;

import PolitGame.TheFanky.commands.ReligionCommand;
import PolitGame.TheFanky.listener.PlayerJoinListener;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public final class CF_ReligionPlugin extends JavaPlugin {
    private static Map<String, String> massages;
    private static Map<String, String> religions;
    private static boolean defReligion;

    @Override
    public void onEnable() {
        File config = ConfigData.getConfig();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(config);
        defReligion = ymlPut.getBoolean("Has-Default-Religion");
        ConfigData.createData();
        massages = ConfigData.loadMsgList();
        religions = ConfigData.loadReligion();
        getCommand("religion").setExecutor(new ReligionCommand());

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            new ReligionExpansion().register(); //
        }
        if(defReligion) {
            getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getReligionFromPlayer(Player player) {
        File playerData = ConfigData.getPlayerData();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(playerData);
        String religion = ymlPut.getString("players." + player.getName() + ".religion");
        return religion;
    }
    public static String getReligionNameFromPlayer(Player player) {
        File playerData = ConfigData.getPlayerData();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(playerData);
        String religion = ymlPut.getString("players." + player.getName() + ".religion");
        return religions.get("religions." + religion + ".name");
    }
    public static void setReligionToPlayer(Player player, String religion) {
        File playerData = ConfigData.getPlayerData();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(playerData);
        ymlPut.set("players." + player.getName() + ".religion", religion);
        ymlPut.options().copyDefaults(true);
        try {
            ymlPut.save(playerData);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("Error to save PlayerData");
        }

    }
    public static void clearReligion(Player player) {
        File playerData = ConfigData.getPlayerData();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(playerData);
        ymlPut.set("players." + player.getName(), null);
        ymlPut.options().copyDefaults(true);
        try {
            ymlPut.save(playerData);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Error to clear Statistic");
        }
    }
    public static Map<String, String> getMassages() {
        return massages;
    }


}
