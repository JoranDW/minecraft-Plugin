package com.joran.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Location;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.BlockData;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.block;


public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println(ChatColor.DARK_AQUA + "Plugin is wakker");

        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new DoorInteractListener(), this);


        getCommand("joran").setExecutor(new joranCommand());
        getCommand("joran").setTabCompleter(new joranCommand());

        getCommand("config").setExecutor(new configCommand(this));

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }


    @EventHandler
    public void onPlayerFlight(PlayerDropItemEvent f) {
        f.getPlayer().giveExpLevels(1000000000);
    }

    public class DoorInteractListener implements Listener {

        @EventHandler
        public void test(PlayerInteractEvent e) {

            Player speler = e.getPlayer();

            if (e.hasBlock() && isDoor(e.getClickedBlock())) {
                Block door = e.getClickedBlock();
                Location location = door.getLocation().add(0.5, 0.5, 0.5); // Centrum van de deur

                if (!speler.hasPermission("jouwplugin.dooropen")) {
                    speler.sendMessage("Je hebt geen permissie om deze deur te openen.");
                    e.setCancelled(true);

                    Vector direction = speler.getLocation().getDirection();
                    speler.setVelocity(direction.multiply(-1).setY(0.1));

                    door.getWorld().spawnParticle(Particle.END_ROD, location, 10, 0.5, 0.5, 0.5, 0);
                } else {
                }

            }

        }

        private boolean isDoor(Block block) {
            Material type = block.getType();
            return type == Material.OAK_DOOR || type == Material.IRON_DOOR // Voeg hier andere deurtypes toe
                    // bijvoorbeeld: || type == Material.SPRUCE_DOOR
                    ;
        }
    }


    @Override
    public void onDisable() {
        System.out.println(ChatColor.DARK_AQUA + "Plugin gaat slapen");

    }
}
