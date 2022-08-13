package com.github.justadeni.creditvoidkill.command;

import com.github.justadeni.HexColorLib;
import com.github.justadeni.creditvoidkill.CreditVoidKill;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    public static HexColorLib hex = HexColorLib.INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!command.getName().equals("creditvoidkill"))
            return true;

        if (sender instanceof Player)
            if (!sender.hasPermission("creditvoidkill.admin"))
                return true;

        if (args.length == 0){
            sender.sendMessage(hex.color("#BA2511Invalid argumentsFF0000"));
        } else if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload")) {
                CreditVoidKill.plugin.reloadConfig();
                CreditVoidKill.plugin.saveConfig();
                sender.sendMessage(hex.color("#196C0FConfig Reloaded#00FF55"));
            } else {
                sender.sendMessage(hex.color("#BA2511Invalid argumentsFF0000"));
            }
        } else {
            sender.sendMessage(hex.color("#BA2511Invalid argumentsFF0000"));
        }
        return true;
    }
}
