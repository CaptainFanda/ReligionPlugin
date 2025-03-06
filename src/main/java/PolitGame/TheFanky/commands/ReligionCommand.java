package PolitGame.TheFanky.commands;

import PolitGame.TheFanky.CF_ReligionPlugin;
import PolitGame.TheFanky.ConfigData;
import PolitGame.TheFanky.ReligionExpansion;
import PolitGame.TheFanky.service.ReligionService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.SplittableRandom;

public class ReligionCommand implements CommandExecutor {
    private String prefix = (String) CF_ReligionPlugin.getMassages().get("prefix");
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player p) {
            File religions = ConfigData.getReligion();
            YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(religions);
            File playerData = ConfigData.getPlayerData();
            YamlConfiguration ymlPutPD = YamlConfiguration.loadConfiguration(playerData);
            if(args.length == 0) {
                String message = prefix + (String) CF_ReligionPlugin.getMassages().get("religion");
                message = message.replace("%religion%", CF_ReligionPlugin.getReligionNameFromPlayer(p));
                p.sendMessage(message);
            } else if(args.length > 1 && args[0].equals("set")) {
                if(p.hasPermission("ReligionPlugin.*") || p.hasPermission("ReligionPlugin.setReligion")) {
                    Player target = (Player) Bukkit.getPlayerExact(args[1]);
                    String religion = (String) args[2];
                    if (ymlPut.contains("religions." + religion)) {
                        CF_ReligionPlugin.setReligionToPlayer(target, religion);
                        String message = prefix + (String) CF_ReligionPlugin.getMassages().get("succes-set-religion");
                        message = message.replace("%player%", target.getName());
                        p.sendMessage(message);
                        List<String> effects = ymlPut.getStringList("religions." + religion + ".effects");
                        for (String effect : effects) {
                            PotionEffectType potionEffectType = PotionEffectType.getByName(effect);
                            if (potionEffectType != null) {
                                PotionEffect potionEffect = new PotionEffect(potionEffectType, Integer.MAX_VALUE, 1);
                                p.addPotionEffect(potionEffect);
                            }
                        }
                    } else {
                        String message = prefix + (String) CF_ReligionPlugin.getMassages().get("not-religion");
                        p.sendMessage(message);
                    }
                }
            } else if(args.length > 1 && args[0].equals("check")) {
                if(p.hasPermission("ReligionPlugin.*") || p.hasPermission("ReligionPlugin.checkReligion")) {

                    Player target = Bukkit.getPlayerExact(args[1]);
                    String message = prefix + CF_ReligionPlugin.getMassages().get("check-religion");
                    message = message.replace("%religion%", CF_ReligionPlugin.getReligionFromPlayer(p));
                    message = message.replace("%player%", target.getName());
                    p.sendMessage(message);
                }
            } else if(args.length > 1 && args[0].equals("buy")) {
                    String religion = args[1];

                    if (!ymlPutPD.contains("players." + p.getName())) {
                        if (ymlPut.contains("religions." + religion)) {
                            List<String> paywall = ymlPut.getStringList("religions." + religion + ".paywall");
                            if (ReligionService.hasRequiredItems(p, paywall)) {
                                ReligionService.removeRequiredItems(p, paywall);
                                CF_ReligionPlugin.setReligionToPlayer(p, religion);
                                List<String> effects = ymlPut.getStringList("religions." + religion + ".effects");

                                for (String effect : effects) {
                                    PotionEffectType potionEffectType = PotionEffectType.getByName(effect);
                                    if (potionEffectType != null) {
                                        PotionEffect potionEffect = new PotionEffect(potionEffectType, Integer.MAX_VALUE, 1);
                                        p.addPotionEffect(potionEffect);
                                    }
                                }
                            } else {
                                String message = prefix + (String) CF_ReligionPlugin.getMassages().get("not-resourses");
                                p.sendMessage(message);
                            }
                        } else {
                            String message = prefix + (String) CF_ReligionPlugin.getMassages().get("not-religion");
                            p.sendMessage(message);
                        }
                    } else {
                        String message = prefix + (String) CF_ReligionPlugin.getMassages().get("have-religion");
                        p.sendMessage(message);
                    }
            } else if (args.length > 1 && args[0].equals("clear")) {
                if(p.hasPermission("ReligionPlugin.*") || p.hasPermission("ReligionPlugin.clearReligion")) {

                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (ymlPutPD.contains("players." + target.getName())) {
                        CF_ReligionPlugin.clearReligion(target);
                        String msgTarget = prefix + (String) CF_ReligionPlugin.getMassages().get("clear-religion-player");
                        String msgAdmin = prefix + (String) CF_ReligionPlugin.getMassages().get("clear-religion-admin");
                        msgAdmin = msgAdmin.replace("%player%", target.getName());
                        for (PotionEffect effect : target.getActivePotionEffects()) {
                            target.removePotionEffect(effect.getType());
                        }
                        target.sendMessage(msgTarget);
                        p.sendMessage(msgAdmin);
                    } else {
                        String message = prefix + (String) CF_ReligionPlugin.getMassages().get("not-player");
                    }
                }
            }
        }
        return true;
    }
}
