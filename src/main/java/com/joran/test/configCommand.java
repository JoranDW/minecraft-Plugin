package com.joran.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class configCommand implements CommandExecutor {

    private Main main;

    public configCommand(Main main){
        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player speler = (Player) sender;

            speler.sendMessage(main.getConfig().getString("word"));


        }
        return false;
    }
}
