package me.reodorfelgen.pathfinder;

import me.reodorfelgen.pathfinder.commands.FindWayHomeCommand;
import me.reodorfelgen.pathfinder.commands.SetHomeCommand;
import me.reodorfelgen.pathfinder.listeners.PlayerMoveListener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public final class PathFinder extends JavaPlugin {


    private static PathFinder plugin;

    // ===========      GETTERS      ===========
    public static PathFinder getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting up the PathFinder plugin...");
        plugin = this;
        getCommand("findwayhome").setExecutor(new FindWayHomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());

        System.out.println("Pathfinder Started.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
