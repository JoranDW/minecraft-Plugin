package com.joran.test;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {
    private List<UUID> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player speler = (Player) sender;


            if (vanished.contains(speler.getUniqueId())){ //Ze zitten in vanish
                vanished.remove(speler.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()){
                    target.hidePlayer(speler);
                }
                speler.sendMessage("niet van");
            }else {//Ze zitten niet in vanish
                vanished.add(speler.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()){
                    target.hidePlayer(speler);
                }

                speler.sendMessage("van");
            }

        }

        return false;
    }
}
