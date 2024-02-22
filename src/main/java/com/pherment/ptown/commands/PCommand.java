package com.pherment.ptown.commands;

import com.pherment.ptown.utils.Town;
import com.pherment.ptown.configs.PConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PCommand implements CommandExecutor {
    private Location pos1;
    private Location pos2;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("PTown.*")) {
            if (args.length == 0) {
                return false;
            }

            if (args[0].equals("create")) {
                if (args.length == 2) {
                    String name = args[1];

                    if (Town.getTownByName(name) != null) {
                        player.sendMessage("Такой город уже есть!");
                        return true;
                    }

                    if (getPos1() == null || getPos2() == null) {
                        player.sendMessage(ChatColor.RED + "Границы не расставлены! Пиши " + ChatColor.GREEN + "/ptown pos1 " + ChatColor.RED + "или " + ChatColor.GREEN + "/ptown pos2");
                        return true;
                    }

                    Town town = new Town(name, pos1, pos2);

                    boolean canCreate = true;

                    for (Town t : Town.townList) {
                        if (!town.doesNotIntersect(t)) {
                            canCreate = false;
                            break;
                        }
                    }

                    if (canCreate) {
                        Town.townList.add(town);

                        PConfig.save();

                        player.sendMessage(ChatColor.GREEN + "Город " + args[1] + " создан!");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Город пересекается с другим городом!");
                        return true;
                    }
                }  else {
                    player.sendMessage(ChatColor.RED + "Вы не ввели имя города! /pt create Name");
                    return true;
                }
            } else if (args[0].equals("remove")) {
                if (args.length == 2) {
                    String name = args[1];

                    if (Town.getTownByName(name) != null) {
                        Town.townList.remove(Town.getTownByName(name));

                        PConfig.save();

                        player.sendMessage(ChatColor.GREEN + "Город удалён!");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Город не найден!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Вы не ввели имя города! /pt create Name");
                    return true;
                }
            } else if (args[0].equals("pos1")) {
                setPos1(player.getLocation());

                player.sendMessage(ChatColor.GREEN + "Первая точка установлена!");

                return true;
            } else if (args[0].equals("pos2")) {
                setPos2(player.getLocation());

                player.sendMessage(ChatColor.GREEN + "Вторая точка установлена!");

                return true;
            } else if (args[0].equals("reload")) {
                PConfig.reload();

                player.sendMessage(ChatColor.GREEN + "Конфиг перезагружен!");
                return true;
            }
        } else {
            player.sendMessage(ChatColor.GREEN + "Вам нужно приобрести привилегию " + ChatColor.GOLD + ChatColor.BOLD + "ВИП " + ChatColor.GREEN + "или выше!");
            return true;
        }

        return false;
    }

    // ============ GETTERS && SETTERS ==============
    public Location getPos1() {
        return pos1;
    }
    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }
    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }
}
