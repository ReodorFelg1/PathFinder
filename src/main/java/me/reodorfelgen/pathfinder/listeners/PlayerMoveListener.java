package me.reodorfelgen.pathfinder.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;


public class PlayerMoveListener implements Listener {

    private final Map<Player, Location> playerLocations = new HashMap<>();


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        playerLocations.put(player, location);
        System.out.println(getPlayerLocation(player));
    }

    public void deactivatePlayer(Player player) {
        playerLocations.remove(player);
    }
    public Location getPlayerLocation(Player player) {
        return playerLocations.get(player);
    }

}
