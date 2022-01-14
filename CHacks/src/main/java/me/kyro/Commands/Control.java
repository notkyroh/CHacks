package me.kyro.Commands;

import me.kyro.Core.CHacksCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Control implements CommandExecutor, Listener {

    HashMap<Player, ItemStack[]> inventario = new HashMap<>();
    HashMap<Player, Location> posizione = new HashMap<>();

    Inventory PlayerGUI;

    CHacksCore main;

    public Control(CHacksCore main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = main.getConfig().getString("Prefix");

        if (sender instanceof Player) {

            Player player = (Player) sender;


            PlayerGUI = Bukkit.createInventory(null, 27, "GUI");

            ItemStack AmmissionePlayer = new ItemStack(Material.RED_WOOL);
            ItemStack ContinuoControllo = new ItemStack(Material.GREEN_WOOL);

            ItemMeta AmmissionePlayerMeta = AmmissionePlayer.getItemMeta();
            ItemMeta ContinuoControlloMeta = ContinuoControllo.getItemMeta();

            List<String> AmmissioneLore = new ArrayList<>();
            List<String> ContinuoControlloLore = new ArrayList<>();

            AmmissionePlayerMeta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Admin to cheat");
            AmmissioneLore.add("");
            AmmissioneLore.add(ChatColor.WHITE + "- Are you" + ChatColor.RED + " sure " + ChatColor.WHITE + "?");
            AmmissioneLore.add("");
            AmmissioneLore.add(ChatColor.WHITE + "- You will receive a" + ChatColor.RED + " halved ban");
            AmmissionePlayerMeta.setLore(AmmissioneLore);


            ContinuoControlloMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + "Continue with the screenshare");
            ContinuoControlloLore.add("");
            ContinuoControlloLore.add(ChatColor.WHITE + "- You will continue" + ChatColor.GREEN + " the screenshare " + ChatColor.WHITE + "regularly");
            ContinuoControlloLore.add("");
            ContinuoControlloMeta.setLore(ContinuoControlloLore);

            AmmissionePlayer.setItemMeta(AmmissionePlayerMeta);
            ContinuoControllo.setItemMeta(ContinuoControlloMeta);

            PlayerGUI.setItem(11, AmmissionePlayer);
            PlayerGUI.setItem(15, ContinuoControllo);


            if (args.length == 1) {
                if (player.hasPermission(main.getConfig().getString("Permesso-Controllo"))) {  // Perms to execute screenshares
                    Player target = Bukkit.getPlayer(args[0]);
                    if (Bukkit.getOnlinePlayers().contains(target)) {
                        if (player == target) {
                            player.sendMessage(prefix + ChatColor.RED + "You cannot choose yourself");
                            return true;
                        }
                        if (main.freeze.contains(target)) {

                            target.sendTitle(ChatColor.GREEN + "Screenshare finished", "",  40, 200, 40);
                            target.sendMessage(prefix + ChatColor.GREEN + "Screenshare finished");

                            main.freeze.remove(target);
                            target.teleport(posizione.get(target));
                            posizione.remove(target);


                            target.getInventory().setContents(inventario.get(target));
                            inventario.remove(target);

                        } else {

                            inventario.put(target, target.getInventory().getContents());
                            posizione.put(target, target.getLocation());
                            main.freeze.add(target);
                            target.teleport(main.PosizioneControllo());
                            target.getInventory().clear();
                            target.openInventory(PlayerGUI);

                        }
                    } else {

                        player.sendMessage(prefix + ChatColor.RED + "The player is not online");

                        return true;
                    }
                }
            } else {

                player.sendMessage(prefix + ChatColor.RED + "Correct use: /control (nick)");

                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        Player player = (Player) e.getPlayer();

        if (main.PlayerIgnorati.contains(player)) {

            return;

        } else {

            Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {

                    player.openInventory(PlayerGUI);

                }
            }, 20L);
        }

    }
}
