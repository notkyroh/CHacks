package me.kyro.Core;

import me.kyro.Commands.Control;
import me.kyro.Commands.CHelp;
import me.kyro.Events.ClickListener;
import me.kyro.Events.FreezeListener;
import me.kyro.TabComplete.TabComplete;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CHacksCore extends JavaPlugin {

    public ArrayList<Player> freeze = new ArrayList<>();
    public List<Player> PlayerIgnorati = new ArrayList<>();
    
    @Override
    public void onEnable() {

        Control cmd = new Control(this);


        System.out.println(ChatColor.GREEN + "» " + "Plugin Enabled!");
        System.out.println(ChatColor.GREEN + "» " + "By kyro#5809");


        /* CONFIG */

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        /* COMANDI */

        getCommand("control").setExecutor(cmd);
        getCommand("ch").setExecutor(new CHelp(this));
        getCommand("ch").setTabCompleter(new TabComplete());


        /* EVENTI */
        Bukkit.getServer().getPluginManager().registerEvents(new FreezeListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(cmd, this);



        if (getConfig().getString("Permesso-Set-Posizione") == null) {
            System.out.println(ChatColor.RED + "CHacks» Setta una posizione nel config");
        }
        if (getConfig().getString("Posizione-Controllo.world") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione (world) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.x") == null) {
            System.out.println(ChatColor.RED + "CHacks» Setta una posizione (x) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.y") == null) {
            System.out.println(ChatColor.RED + "CHacks» Setta una posizione (y) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.z") == null) {
            System.out.println(ChatColor.RED + "CHacks» Setta una posizione (z) nel config");
        }
        if (getConfig().getString("Permesso-Reload") == null) {
            System.out.println(ChatColor.RED + "CHacks» Setta i permessi di reload");
        }



    }

    public void onDisable() {
        System.out.println(ChatColor.RED + "» " + "Plugin Disabilitato!");
        System.out.println(ChatColor.RED+ "» " + "By kyro#5809");

    }

    public Location PosizioneControllo() {
        return new Location(Bukkit.getWorld(getConfig().getString("Posizione-Controllo.world")),
                getConfig().getInt("Posizione-Controllo.x"),
                getConfig().getInt("Posizione-Controllo.y"),
                getConfig().getInt("Posizione-Controllo.z"));
    }
}
