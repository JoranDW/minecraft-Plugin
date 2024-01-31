package com.joran.test;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class joranCommand implements CommandExecutor, TabCompleter {
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
                    } else{
                        speler.sendMessage(ChatColor.DARK_RED + "Sorry, Je hebt geen perms om deze command uit te voeren");
                    }
                }
            }




        }else{
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
            // Je kunt hier meer argumenten toevoegen
        }

        return completions;
    }

}
