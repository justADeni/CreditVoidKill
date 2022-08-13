package com.github.justadeni.creditvoidkill.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("creditvoidkill")) {
            if (args.length == 1) {
                final ArrayList<String> l = new ArrayList<>();
                final ArrayList<String> commands = new ArrayList<>();
                if (sender.hasPermission("creditvoidkill.admin")){
                    commands.add("reload");
                }

                StringUtil.copyPartialMatches(args[0], commands, l);

                return l;
            }
        }
        return null;
    }
}
