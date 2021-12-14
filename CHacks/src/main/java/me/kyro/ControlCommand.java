package me.kyro;

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

public class ControlCommand implements CommandExecutor, Listener {

    HashMap<Player, ItemStack[]> inventario = new HashMap<>();
    HashMap<Player, Location> posizione = new HashMap<>();

    Inventory PlayerGUI;

    Main main;

    public ControlCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            Inventory Commands = Bukkit.createInventory(null, 27, "GUI");

            ItemStack NoHack = new ItemStack(Material.GREEN_WOOL);
            ItemStack HackTrovate = new ItemStack(Material.RED_WOOL);

            ItemMeta NoHackMeta = NoHack.getItemMeta();
            ItemMeta HackTrovateMeta = HackTrovate.getItemMeta();

            NoHackMeta.setDisplayName(ChatColor.GREEN + "Utente Pulito");
            HackTrovateMeta.setDisplayName(ChatColor.RED + "Hack Trovate");

            NoHack.setItemMeta(NoHackMeta);
            HackTrovate.setItemMeta(HackTrovateMeta);

            Commands.setItem(11, NoHack);
            Commands.setItem(15, HackTrovate);


            PlayerGUI = Bukkit.createInventory(null, 27, "GUI");

            ItemStack AmmissionePlayer = new ItemStack(Material.RED_WOOL);
            ItemStack ContinuoControllo = new ItemStack(Material.GREEN_WOOL);

            ItemMeta AmmissionePlayerMeta = AmmissionePlayer.getItemMeta();
            ItemMeta ContinuoControlloMeta = ContinuoControllo.getItemMeta();

            List<String> AmmissioneLore = new ArrayList<>();
            List<String> ContinuoControlloLore = new ArrayList<>();

            AmmissionePlayerMeta.setDisplayName(ChatColor.RED + "" + ChatColor.ITALIC + "Ammetto di usare Hack");
            AmmissioneLore.add("");
            AmmissioneLore.add(ChatColor.WHITE + "- Sicuro di voler" + ChatColor.RED + " ammettere " + ChatColor.WHITE + "?");
            AmmissioneLore.add("");
            AmmissioneLore.add(ChatColor.WHITE + "- Riceverai un ban" + ChatColor.RED + " dimezzato");
            AmmissionePlayerMeta.setLore(AmmissioneLore);


            ContinuoControlloMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + "Continuo con il controllo");
            ContinuoControlloLore.add("");
            ContinuoControlloLore.add(ChatColor.WHITE + "- Continuerai il" + ChatColor.GREEN + " controllo " + ChatColor.WHITE + "regolarmente");
            ContinuoControlloLore.add("");
            ContinuoControlloMeta.setLore(ContinuoControlloLore);

            AmmissionePlayer.setItemMeta(AmmissionePlayerMeta);
            ContinuoControllo.setItemMeta(ContinuoControlloMeta);

            PlayerGUI.setItem(11, AmmissionePlayer);
            PlayerGUI.setItem(15, ContinuoControllo);


            if (args.length == 1) {
                if (player.hasPermission(main.getConfig().getString("Permesso-Controllo"))) {  // Permesso per eseguire controlli
                    Player target = Bukkit.getPlayer(args[0]);
                    if (Bukkit.getOnlinePlayers().contains(target)) {
                        if (player == target) {
                            player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.RED + "Non puoi scegliere te stesso");
                            return true;
                        }
                        if (main.freeze.contains(target)) {

                            target.sendTitle(ChatColor.GREEN + "Controllo Hack finito", "",  40, 200, 40);
                            target.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.GREEN + "Controllo Hack Finito");

                            main.freeze.remove(target);
                            target.teleport(posizione.get(target));
                            posizione.remove(target);


                            target.getInventory().setContents(inventario.get(target));
                            inventario.remove(target);

                            if(player.hasPermission("ch.staff")){
                                player.openInventory(Commands);
                            }

                        } else {

                            inventario.put(target, target.getInventory().getContents());
                            posizione.put(target, target.getLocation());
                            main.freeze.add(target);
                            target.teleport(main.PosizioneControllo());
                            target.getInventory().clear();
                            target.openInventory(PlayerGUI);

                        }
                    } else {

                        player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.RED + "Il player non è online");

                        return true;
                    }
                }
            } else {

                player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.RED + "Uso corretto: /controllo (nome)");

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
