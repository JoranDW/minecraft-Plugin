package com.joran.test;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class joranCommand implements CommandExecutor, TabCompleter {

    public static boolean isLoggerOn = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player speler = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("heal")) {
                    if (speler.hasPermission("joranPlugin.heal")) {
                        speler.setHealth(20);
                        speler.setFoodLevel(40);
                    } else {
                        speler.sendMessage(ChatColor.DARK_RED + "Sorry, Je hebt geen perms om deze command uit te voeren");
                    }

                } else if (args[0].equalsIgnoreCase("fly")) {

                    if (speler.hasPermission("joranPlugin.fly")) {

                        if (speler.getAllowFlight()) {
                            speler.sendMessage("je kan niet meer vliegen");
                            speler.setAllowFlight(false);
                            speler.setFlying(false);

                        } else {
                            speler.sendMessage("Je kan nu vliegen");
                            speler.setAllowFlight(true);
                            speler.setFlying(true);
                        }
                    } else {
                        speler.sendMessage(ChatColor.DARK_RED + "Sorry, Je hebt geen perms om deze command uit te voeren");
                    }
                } else if (args[0].equalsIgnoreCase("gmc")) {
                    if (speler.hasPermission("joranPlugin.gmc")) {
                        speler.setGameMode(GameMode.CREATIVE);
                        speler.sendMessage(ChatColor.GOLD + "Je gamemode is veranderd naar " + speler.getGameMode());

                    }
                } else if (args[0].equalsIgnoreCase("gma")) {
                    if (speler.hasPermission("joranPlugin.gma")) {
                        speler.setGameMode(GameMode.ADVENTURE);
                        speler.sendMessage(ChatColor.GOLD + "Je gamemode is veranderd naar " + speler.getGameMode());


                    }
                } else if (args[0].equalsIgnoreCase("gmsp")) {
                    if (speler.hasPermission("joranPlugin.gmsp")) {
                        speler.setGameMode(GameMode.SPECTATOR);
                        speler.sendMessage(ChatColor.GOLD + "Je gamemode is veranderd naar " + speler.getGameMode());


                    }
                } else if (args[0].equalsIgnoreCase("gms")) {
                    if (speler.hasPermission("joranPlugin.gms")) {
                        speler.setGameMode(GameMode.SURVIVAL);
                        speler.sendMessage(ChatColor.GOLD + "Je gamemode is veranderd naar " + speler.getGameMode());


                    }
                } else if (args[0].equalsIgnoreCase("logger")) {
                    if (speler.hasPermission("joranPlugin.logger")){
                        if (isLoggerOn == true){
                            isLoggerOn = false;
                            speler.sendMessage(ChatColor.DARK_RED.BOLD + "Je hebt de console logger uitgezet!");
                        } else if (isLoggerOn == false) {
                            isLoggerOn = true;
                            speler.sendMessage(ChatColor.DARK_RED.BOLD + "Je hebt de console logger aangezet!");
                        }

                    }
                } else if (args[0].equalsIgnoreCase("vanish")) {
                    if (speler.hasPermission("joranPlugin.vanish")){

                    }
                    
                }
            }


        } else {
            System.out.println("Helaas je bent geen speler dus dit gaat niet lukken.");
        }


        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        // Voor het eerste argument, voeg "heal" toe als mogelijkheid
        if (args.length == 1) {
            completions.add("heal");
            completions.add("fly");
            completions.add("gmc");
            completions.add("gms");
            completions.add("gmsp");
            completions.add("gma");
            completions.add("vanish");
            completions.add("logger");
            // Je kunt hier meer argumenten toevoegen
        }

        return completions;
    }

}
