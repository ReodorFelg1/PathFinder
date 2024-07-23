package me.reodorfelgen.pathfinder;

import me.reodorfelgen.pathfinder.commands.FindWayHomeCommand;
import me.reodorfelgen.pathfinder.commands.SetHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

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

        // Create a single instance of SetHomeCommand
        SetHomeCommand setHomeCommand = new SetHomeCommand();

        // Register commands using the shared instance of SetHomeCommand
        getCommand("findwayhome").setExecutor(new FindWayHomeCommand(setHomeCommand));
        getCommand("sethome").setExecutor(setHomeCommand);

        System.out.println("Pathfinder Started.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
