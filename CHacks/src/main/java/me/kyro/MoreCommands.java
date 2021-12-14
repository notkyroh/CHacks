package me.kyro;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoreCommands implements CommandExecutor {

    Main main;

    public MoreCommands(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 0) {
                return true;
            }


            if (args[0].equalsIgnoreCase("help"))
                player.sendMessage("");
                player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "CHACKS PLUGIN");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + ">" + ChatColor.AQUA + "/controllo " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Avvia il controllo a un player");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + ">" + ChatColor.AQUA + "/ch setposizione " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Setta la posizione dei controlli hack");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + ">" + ChatColor.AQUA + "/ch help" + ChatColor.WHITE + ChatColor.ITALIC + "- Lista dei comandi disponibili");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + ">" + ChatColor.AQUA + "/ch reload " + ChatColor.WHITE + "" + ChatColor.ITALIC + "- Reload del config");
                player.sendMessage("");
                player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Plugin by kyro#5809");


                if (args[0].equalsIgnoreCase("reload"))
                    if (player.hasPermission(main.getConfig().getString("Permesso-Reload"))) {

                        main.reloadConfig();
                        player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.GREEN + "Config riavviato con successo!");

                    } else {
                        player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.RED + "Non puoi");
                    }


                if (args[0].equalsIgnoreCase("setposizione"))
                        if (player.hasPermission(main.getConfig().getString("Permesso-Set-Posizione"))) {

                            main.getConfig().set("Posizione-Controllo.world", player.getLocation().getWorld().getName());
                            main.getConfig().set("Posizione-Controllo.x", player.getLocation().getX());
                            main.getConfig().set("Posizione-Controllo.y", player.getLocation().getY());
                            main.getConfig().set("Posizione-Controllo.z", player.getLocation().getZ());
                            main.saveConfig();
                            player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.GREEN + "Posizione settata");
                        }
                    }
        return false;
            }
        }

