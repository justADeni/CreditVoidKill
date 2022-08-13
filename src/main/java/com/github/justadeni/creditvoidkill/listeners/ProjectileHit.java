package com.github.justadeni.creditvoidkill.listeners;

import com.github.justadeni.creditvoidkill.CreditVoidKill;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileHit implements Listener {

    @EventHandler
    public void onArrowCollide(ProjectileHitEvent e){
        new BukkitRunnable() {
            public void run() {
                if (EntityDamage.arrowMap != null){
                    EntityDamage.arrowMap.remove(e.getEntity().getEntityId());
                }
            }
        }.runTaskLaterAsynchronously(CreditVoidKill.plugin, 20L);
    }

}
