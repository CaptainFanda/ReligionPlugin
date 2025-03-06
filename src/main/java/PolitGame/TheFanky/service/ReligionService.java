package PolitGame.TheFanky.service;

import PolitGame.TheFanky.ConfigData;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ReligionService {
    public static boolean hasRequiredItems(Player player, List<String> paywall) {
        for (String item : paywall) {
            String[] parts = item.split(":");
            Material material = Material.getMaterial(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            if (material != null && player.getInventory().contains(material, amount)) {
                continue;
            }
            return false;
        }
        return true;
    }
    public static void removeRequiredItems(Player player, List<String> paywall) {
        for (String item : paywall) {
            String[] parts = item.split(":");
            Material material = Material.getMaterial(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            if (material != null) {
                player.getInventory().removeItem(new ItemStack(material, amount));
            }
        }
    }
    public static void giveReligion(Player player, String religion) {
        File playerData = ConfigData.getPlayerData();
        Map<String, String> religions = ConfigData.loadReligion();
        YamlConfiguration ymlPutData = YamlConfiguration.loadConfiguration(playerData);
        if(religion.contains("religions." + religion)) {
            ymlPutData.addDefault("players." + player.getName() + ".religion", religion);
        }
    }

}
