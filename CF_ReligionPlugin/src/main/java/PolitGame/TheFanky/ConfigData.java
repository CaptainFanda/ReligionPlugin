package PolitGame.TheFanky;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigData {
    private static File config = new File("plugins/CF_ReligionPlugin/config.yml");
    private static File message = new File("plugins/CF_ReligionPlugin/message.yml");
    private static File playerData = new File("plugins/CF_ReligionPlugin/playerData.yml");
    private static File religions = new File("plugins/CF_ReligionPlugin/religions.yml");

    public static void createData() {
        YamlConfiguration ymlPut;
        if(!config.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(config);
            Map<String, Object> configList = new HashMap<>();
            configList.put("Has-Default-Religion", false);
            configList.put("Default-Religion", "Catholicism");
            ymlPut.addDefaults(configList);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(config);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("Error To Create Config.yml");
            }
        }
        if(!religions.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(religions);
            String name = "&e&cCatholicism";
            List<String> effectsList  = new ArrayList<>();
            effectsList.add("REGENERATION");
            List<String> paywallList = new ArrayList<>();
            paywallList.add("DIRT:128");
            paywallList.add("COBBLESTONE:128");
            ymlPut.addDefault("religions.Catholicism.name", name);
            ymlPut.addDefault("religions.Catholicism.effects", effectsList);
            ymlPut.addDefault("religions.Catholicism.paywall", paywallList);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(religions);
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Error to create Religions.yml");
            }
        }
        if(!message.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(message);
            LinkedHashMap<String, Object> msgList = new LinkedHashMap<>();
            msgList.put("prefix", "[&eReligionPlugin&f] ");
            msgList.put("not-permission", "&cВы не Имеете Права");
            msgList.put("religion", "&aВаша Религия: %religion%");
            msgList.put("check-religion", "&aРелигия игрока %player%: %religion%");
            msgList.put("succes-set-religion", "&aВы успешно установили религию игроку: %player%");
            msgList.put("have-religion", "&cУ вас уже Есть Религия");
            msgList.put("not-religion", "&cРелигия Не Найдена");
            msgList.put("not-resourses", "&cНедостаточно Ресурсов");
            msgList.put("clear-religion-admin", "&cВы очистили Религию: %player%");
            msgList.put("clear-religion-player", "&cВам Очистили Религию");
            msgList.put("not-player", "&cИгрок Не Найден");
            ymlPut.addDefaults(msgList);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(message);
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Error to create message.yml");
            }
        }
    }
    public static Map<String, String> loadMsgList() {
        Map<String, String> map = new HashMap<>();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(message);
        Set<String> set = ymlPut.getKeys(true);
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String msg = ymlPut.getString(key);
            String msg1 = msg.replace('&', '§');
            map.put(key, msg1);
        }
        return map;
    }
    public static Map<String, String> loadReligion() {
        Map<String, String> map = new HashMap<>();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(religions);
        Set<String> set = ymlPut.getKeys(true);
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String msg = ymlPut.getString(key);
            String msg1 = msg.replace('&', '§');
            map.put(key, msg1);
        }
        return map;
    }

    public static File getConfig() {
        return config;
    }
    public static File getReligion() { return religions; }
    public static File getPlayerData() {
        return playerData;
    }


}
