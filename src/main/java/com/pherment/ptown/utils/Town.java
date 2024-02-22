package com.pherment.ptown.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Town {
    public static List<Town> townList = new ArrayList<>();
    public List<Player> playerEntered = new ArrayList<>();

    public boolean playerIsEntered(Player player) {
        for (Player p : getPlayerEntered()) {
            if (p.getUniqueId().equals(player.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

    public static Town getTownByName(String name) {
        for (Town t: townList) {
            if (t.name.equals(name)) {
                return t;
            }
        }
        return null;
    }

    public boolean doesNotIntersect(Town otherRegion) {
        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        int otherMinX = Math.min(otherRegion.pos1.getBlockX(), otherRegion.pos2.getBlockX());
        int otherMaxX = Math.max(otherRegion.pos1.getBlockX(), otherRegion.pos2.getBlockX());
        int otherMinY = Math.min(otherRegion.pos1.getBlockY(), otherRegion.pos2.getBlockY());
        int otherMaxY = Math.max(otherRegion.pos1.getBlockY(), otherRegion.pos2.getBlockY());
        int otherMinZ = Math.min(otherRegion.pos1.getBlockZ(), otherRegion.pos2.getBlockZ());
        int otherMaxZ = Math.max(otherRegion.pos1.getBlockZ(), otherRegion.pos2.getBlockZ());

        boolean notIntersect = maxX < otherMinX || minX > otherMaxX ||
                maxY < otherMinY || minY > otherMaxY ||
                maxZ < otherMinZ || minZ > otherMaxZ;

        boolean notInside = minX < otherMinX || maxX > otherMaxX ||
                minY < otherMinY || maxY > otherMaxY ||
                minZ < otherMinZ || maxZ > otherMaxZ;

        return notIntersect && notInside;
    }

    private String name;
    private Location pos1;
    private Location pos2;

    public Town(String name, Location pos1, Location pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public boolean containsLocation(Location location) {
        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
    }

    // =========== GETTERS && SETTERS ==============
    public String getName() {
        return name;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public List<Player> getPlayerEntered() {
        return playerEntered;
    }
    public void setPlayerEntered(List<Player> playerEntered) {
        this.playerEntered = playerEntered;
    }
}
