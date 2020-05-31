package me.prostedeni.goodcraft.creditvoidkill;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CreditVoidKill extends JavaPlugin implements Listener {

    HashMap<String, String> map = new HashMap<>();
    HashMap<String, Integer> timerMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    @EventHandler
    public void onPlayerGetHit(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            if (map.containsKey(e.getEntity().getName())){
                if (!(map.get(e.getEntity().getName()).equals(e.getDamager().getName()))) {
                    map.remove(e.getEntity().getName());
                    map.put(e.getEntity().getName(), e.getDamager().getName());

                    if (timerMap.get(e.getEntity().getName()) != null) {
                        if (timerMap.get(e.getEntity().getName()) != 1) {
                            timerMap.remove(e.getEntity().getName());
                            timerMap.put(e.getEntity().getName(), 1);
                        }
                    }

                    Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if (timerMap.get(e.getEntity().getName()) != null) {
                                if (timerMap.get(e.getEntity().getName()) == 1) {
                                    if (map.get(e.getEntity().getName()) != null) {
                                        map.remove(e.getEntity().getName());
                                    }
                                    timerMap.remove(e.getEntity().getName());
                                }
                            }
                        }
                    }, (getConfig().getInt("Timer") * 20L));
                } else {
                    map.put(e.getEntity().getName(), e.getDamager().getName());

                    if (timerMap.get(e.getEntity().getName()) != null) {
                        if (timerMap.get(e.getEntity().getName()) != 2) {
                            timerMap.remove(e.getEntity().getName());
                            timerMap.put(e.getEntity().getName(), 2);
                        }
                    }

                    Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if (timerMap.get(e.getEntity().getName()) != null) {
                                if (timerMap.get(e.getEntity().getName()) == 2) {
                                    if (map.get(e.getEntity().getName()) != null) {
                                        map.remove(e.getEntity().getName());
                                    }
                                    timerMap.remove(e.getEntity().getName());
                                }
                            }
                        }
                    }, (getConfig().getInt("Timer") * 20L));
                }
            } else {
                map.put(e.getEntity().getName(), e.getDamager().getName());

                if (timerMap.get(e.getEntity().getName()) != null) {
                    if (timerMap.get(e.getEntity().getName()) != 3) {
                        timerMap.remove(e.getEntity().getName());
                        timerMap.put(e.getEntity().getName(), 3);
                    }
                }

                Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (timerMap.get(e.getEntity().getName()) != null) {
                            if (timerMap.get(e.getEntity().getName()) == 3) {
                                if (map.get(e.getEntity().getName()) != null) {
                                    map.remove(e.getEntity().getName());
                                }
                                timerMap.remove(e.getEntity().getName());
                            }
                        }
                    }
                }, (getConfig().getInt("Timer") * 20L));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerVoidDeath(PlayerDeathEvent e){
        if (map.containsKey(e.getEntity().getName())) {
            if (e.getEntity().getLocation().getBlockY() < 0) {

                String replace1 = getConfig().getString("KillMessage").replaceFirst("<player1>", e.getEntity().getName());
                String replace2 = replace1.replaceFirst("<player2>", map.get(e.getEntity().getName()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', replace2));

                if (e.getEntity().getPlayer().getScoreboard().getObjective("playerKills") != null){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add " + map.get(e.getEntity().getName()) + " playerKills 1");
                }

                if (map.get(e.getEntity().getName()) != null) {
                    map.remove(e.getEntity().getName());
                }
                if (timerMap.get(e.getEntity().getName()) != null) {
                    timerMap.remove(e.getEntity().getName());
                }
            } else {
                map.remove(e.getEntity().getName());
                if (timerMap.get(e.getEntity().getName()) != null) {
                    timerMap.remove(e.getEntity().getName());
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (sender.hasPermission("creditvoidkill.admin")){
                if (command.getName().equals("creditvoidkill")){
                    if (args.length == 0){
                        sender.sendMessage(ChatColor.DARK_RED + "No arguments detected");
                    } else if (args.length == 1){
                        if (args[0].equalsIgnoreCase("reload")) {
                            reloadConfig();
                            getConfig();
                            saveConfig();
                            sender.sendMessage(ChatColor.DARK_GREEN + "Config reloaded");
                            System.out.println(ChatColor.DARK_GREEN + "Config reloaded");
                        } else if (!(args[0].equalsIgnoreCase("reload"))){
                            sender.sendMessage(ChatColor.DARK_RED + "Invalid arguments");
                        }
                    } else if (args.length > 1){
                        sender.sendMessage(ChatColor.DARK_RED + "Invalid arguments");
                    }
                }
            }
        } else {
            if (command.getName().equals("creditvoidkill")){
                if (args.length == 0){
                    sender.sendMessage(ChatColor.DARK_RED + "No arguments detected");
                } else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        getConfig();
                        saveConfig();
                        sender.sendMessage(ChatColor.DARK_GREEN + "Config reloaded");
                    } else if (!(args[0].equalsIgnoreCase("reload"))){
                        sender.sendMessage(ChatColor.DARK_RED + "Invalid arguments");
                    }
                } else if (args.length > 1){
                    sender.sendMessage(ChatColor.DARK_RED + "Invalid arguments");
                }
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
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
