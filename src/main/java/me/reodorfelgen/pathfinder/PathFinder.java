package me.reodorfelgen.pathfinder;

import me.reodorfelgen.pathfinder.commands.FindWayHomeCommand;
import me.reodorfelgen.pathfinder.commands.SetHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PathFinder extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("findwayhome").setExecutor(new FindWayHomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
