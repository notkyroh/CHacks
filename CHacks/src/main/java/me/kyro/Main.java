package me.kyro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    ArrayList<Player> freeze = new ArrayList<>();
    List<Player> PlayerIgnorati = new ArrayList<>();
    
    @Override
    public void onEnable() {

        ControlCommand cmd = new ControlCommand(this);


        System.out.println(ChatColor.GREEN + ">> " + "Plugin Abilitato!");
        System.out.println(ChatColor.GREEN + ">> " + "By kyro#5809");

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("controllo").setExecutor(cmd);
        getCommand("ch").setExecutor(new MoreCommands(this));
        getCommand("ch").setTabCompleter(new TabComplete());

        Bukkit.getServer().getPluginManager().registerEvents(new FreezeListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(cmd, this);



        if (getConfig().getString("Permesso-Set-Posizione") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione nel config");
        }
        if (getConfig().getString("Posizione-Controllo.world") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione (world) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.x") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione (x) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.y") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione (y) nel config");
        }
        if (getConfig().getString("Posizione-Controllo.z") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta una posizione (z) nel config");
        }
        if (getConfig().getString("Permesso-Reload") == null) {
            System.out.println(ChatColor.RED + "CHacks>> Setta i permessi di reload");
        }



    }

    public void onDisable() {
        System.out.println(ChatColor.RED + ">> " + "Plugin Disabilitato!");
        System.out.println(ChatColor.RED+ ">> " + "By kyro#5809");

    }

    public Location PosizioneControllo() {

        return new Location(Bukkit.getWorld(getConfig().getString("Posizione-Controllo.world")),
                getConfig().getInt("Posizione-Controllo.x"),
                getConfig().getInt("Posizione-Controllo.y"),
                getConfig().getInt("Posizione-Controllo.z"));
    }
}
