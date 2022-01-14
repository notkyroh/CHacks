package me.kyro.Events;

import me.kyro.Core.CHacksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickListener implements Listener {

    CHacksCore main;

    /* CONSTRUCTOR */
    public ClickListener(CHacksCore main) {
        this.main = main;
    }



    @EventHandler
    public void onClick(InventoryClickEvent e) {

        String prefix = main.getConfig().getString("Prefix");

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("GUI")) {

                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.ITALIC + "Continue with the screenshare")) {



                        main.PlayerIgnorati.add(player);
                        player.closeInventory();
                        player.sendTitle(ChatColor.RED + "Screenshare", ChatColor.RED + "Follow the instruction", 20, 100, 20);
                        player.sendMessage(prefix + ChatColor.RED + "Screenshare");

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.ITALIC + "Admit to using cheats")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Command-Console-Ban").replaceAll("%player%", player.getName()));

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Admit")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Command-Console-Ban"));

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Tempban")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Command-Staff-Tempban"));
                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Permaban")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Command-Staff-Permaban"));
                    }
                }
            }
        }
    }
