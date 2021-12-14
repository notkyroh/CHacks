package me.kyro;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeListener implements Listener {

    Main main;

    public FreezeListener(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();

        if (main.freeze.contains(player)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        main.freeze.remove(player);
        main.PlayerIgnorati.remove(player);

    }
}
