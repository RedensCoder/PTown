package com.pherment.ptown.events;

import com.pherment.ptown.utils.Town;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegionListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        for (Town town : Town.townList) {
            if (town.containsLocation(playerLocation) && !town.playerIsEntered(player)) {
                town.playerEntered.add(player);
                player.sendTitle(ChatColor.GOLD + town.getName(), ChatColor.GREEN + "Добро пожаловать!", 10, 70, 20);
                break;
            } else if (!town.containsLocation(playerLocation) && town.playerIsEntered(player)) {
                town.playerEntered.remove(player);
                break;
            }
        }
    }
}
