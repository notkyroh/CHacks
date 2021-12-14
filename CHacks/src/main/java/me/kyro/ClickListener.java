package me.kyro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickListener implements Listener {

    Main main;

    public ClickListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("GUI")) {

            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Utente Pulito")) {

                    player.closeInventory();

                }

                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Hack Trovate")) {
                        player.closeInventory();


                        Inventory Bans = Bukkit.createInventory(null, 27, "BANS");

                        ItemStack Ammissione = new ItemStack(Material.NETHER_STAR);
                        ItemStack Tempban = new ItemStack(Material.NETHER_STAR);
                        ItemStack Permaban = new ItemStack(Material.NETHER_STAR);

                        ItemMeta AmmissioneMeta = Ammissione.getItemMeta();
                        ItemMeta TempbanMeta = Tempban.getItemMeta();
                        ItemMeta PermabanMeta = Permaban.getItemMeta();

                        AmmissioneMeta.setDisplayName(ChatColor.GREEN + "Ammissione");
                        TempbanMeta.setDisplayName(ChatColor.RED + "Tempban");
                        PermabanMeta.setDisplayName(ChatColor.RED + "Permaban");

                        Ammissione.setItemMeta(AmmissioneMeta);
                        Tempban.setItemMeta(TempbanMeta);
                        Permaban.setItemMeta(PermabanMeta);

                        Bans.setItem(10, Ammissione);
                        Bans.setItem(13, Tempban);
                        Bans.setItem(16, Permaban);

                        player.openInventory(Bans);

                    }
                }

                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.ITALIC + "Continuo con il controllo")) {


                        main.PlayerIgnorati.add(player);
                        player.closeInventory();
                        player.sendTitle(ChatColor.RED + "Controllo Hack", ChatColor.RED + "Segui le istruzioni dello staff", 20, 100, 20);
                        player.sendMessage(ChatColor.GRAY + "CHacks>> " + ChatColor.RED + "Sei sotto controllo Hack");

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.ITALIC + "Ammetto di usare Hack")) {


                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Comando-Console-Ban").replaceAll("%player%", player.getName()));

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Ammissione")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Comando-Console-Ban"));

                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Tempban")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Comando-Staff-Tempban"));
                    }
                }
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Permaban")) {

                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), main.getConfig().getString("Comando-Staff-Permaban"));
                    }
                }
            }
        }
    }
}
