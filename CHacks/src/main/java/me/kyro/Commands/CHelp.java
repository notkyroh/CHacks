package me.kyro.Commands;

import me.kyro.Core.CHacksCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CHelp implements CommandExecutor {

    CHacksCore main;

    public CHelp(CHacksCore main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = main.getConfig().getString("Prefix");

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 0) {
                return true;
            }


            if (args[0].equalsIgnoreCase("help"))
                player.sendMessage("");
                player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "CHACKS PLUGIN");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "»" + ChatColor.AQUA + "/control " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Start the screenshare");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "»" + ChatColor.AQUA + "/ch setposition " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Set control hack position");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "»" + ChatColor.AQUA + "/ch help" + ChatColor.WHITE + ChatColor.ITALIC + "- List of available commands");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "»" + ChatColor.AQUA + "/ch reload " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Config reload");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Plugin by kyro#5809");


                if (args[0].equalsIgnoreCase("reload"))
                    if (player.hasPermission(main.getConfig().getString("Permesso-Reload"))) {

                        main.reloadConfig();
                        player.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");

                    } else {
                        player.sendMessage(prefix + ChatColor.RED + "No permission");
                    }


                if (args[0].equalsIgnoreCase("setposition"))
                        if (player.hasPermission(main.getConfig().getString("Permesso-Set-Posizione"))) {

                            main.getConfig().set("Posizione-Controllo.world", player.getLocation().getWorld().getName());
                            main.getConfig().set("Posizione-Controllo.x", player.getLocation().getX());
                            main.getConfig().set("Posizione-Controllo.y", player.getLocation().getY());
                            main.getConfig().set("Posizione-Controllo.z", player.getLocation().getZ());
                            main.saveConfig();
                            player.sendMessage(prefix + ChatColor.GREEN + "Position set successfully");
                        }
                    }
        return false;
            }
        }

