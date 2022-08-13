package com.github.justadeni.creditvoidkill.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.Objects;

public class EntityShoot implements Listener {

    @EventHandler
    public void onPlayerFireArrow(EntityShootBowEvent e){
        if (e.getEntity() instanceof Player){
            EntityDamage.arrowMap.put(e.getProjectile().getEntityId(), Objects.requireNonNull(((Player) e.getEntity()).getPlayer()).getName());
        }
    }

}
