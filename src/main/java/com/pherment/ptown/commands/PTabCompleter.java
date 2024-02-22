package com.pherment.ptown.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class PTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.isOp()) {
            if (args.length == 1) {
                return List.of(
                        "pos1",
                        "pos2",
                        "create",
                        "remove",
                        "reload"
                );
            }
        }

        if (player.hasPermission("PTown.*")) {
            if (args.length == 1) {
                return List.of(
                        "pos1",
                        "pos2",
                        "create",
                        "remove"
                );
            }
        }

        return null;
    }
}
