package com.github.justadeni.creditvoidkill.listeners;

import com.github.justadeni.creditvoidkill.CreditVoidKill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class PlayerVoidDeath implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerVoidDeath(PlayerDeathEvent e){
        if (EntityDamage.map.containsKey(e.getEntity().getName())) {
            if (e.getEntity().getLocation().getBlockY() < 0) {

                String replace1 = CreditVoidKill.plugin.getConfig().getString("KillMessage").replaceFirst("%player1%", e.getEntity().getName());
                String replace2 = replace1.replaceFirst("%player2%", EntityDamage.map.get(e.getEntity().getName()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', replace2));

                if (CreditVoidKill.plugin.getConfig().getBoolean("Command")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), CreditVoidKill.plugin.getConfig().getString("RunCommand").replaceFirst("%player2%", EntityDamage.map.get(e.getEntity().getName())));
                }

                if (EntityDamage.map.get(e.getEntity().getName()) != null) {
                    EntityDamage.map.remove(e.getEntity().getName());
                }
                if (EntityDamage.timerMap.get(e.getEntity().getName()) != null) {
                    EntityDamage.timerMap.remove(e.getEntity().getName());
                }
            } else {
                EntityDamage.map.remove(e.getEntity().getName());
                if (EntityDamage.timerMap.get(e.getEntity().getName()) != null) {
                    EntityDamage.timerMap.remove(e.getEntity().getName());
                }
            }
        }
    }

}
