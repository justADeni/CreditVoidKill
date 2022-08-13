package com.github.justadeni.creditvoidkill.listeners;

import com.github.justadeni.creditvoidkill.CreditVoidKill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class EntityDamage implements Listener {
    public static HashMap<String, String> map = new HashMap<>();
    public static HashMap<String, Integer> timerMap = new HashMap<>();
    public static HashMap<Integer, String> arrowMap = new HashMap<>();

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

                    entityTask(e.getEntity(), 1);
                } else {
                    map.put(e.getEntity().getName(), e.getDamager().getName());

                    if (timerMap.get(e.getEntity().getName()) != null) {
                        if (timerMap.get(e.getEntity().getName()) != 2) {
                            timerMap.remove(e.getEntity().getName());
                            timerMap.put(e.getEntity().getName(), 2);
                        }
                    }

                    entityTask(e.getEntity(), 2);
                }
            } else {
                map.put(e.getEntity().getName(), e.getDamager().getName());

                if (timerMap.get(e.getEntity().getName()) != null) {
                    if (timerMap.get(e.getEntity().getName()) != 3) {
                        timerMap.remove(e.getEntity().getName());
                        timerMap.put(e.getEntity().getName(), 3);
                    }
                }

                entityTask(e.getEntity(), 3);
            }

            // Arrow part here

        } else if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player){
            if (arrowMap != null) {
                if (arrowMap.containsKey(e.getDamager().getEntityId())) {
                    if (map.containsKey(e.getEntity().getName())){
                        if (!(map.get(e.getEntity().getName()).equals(e.getDamager().getName()))) {
                            map.remove(e.getEntity().getName());
                            map.put(e.getEntity().getName(), arrowMap.get(e.getDamager().getEntityId()));

                            if (timerMap.get(e.getEntity().getName()) != null) {
                                if (timerMap.get(e.getEntity().getName()) != 4) {
                                    timerMap.remove(e.getEntity().getName());
                                    timerMap.put(e.getEntity().getName(), 4);
                                }
                            }

                            entityTask(e.getEntity(), 4);
                        } else {
                            map.put(e.getEntity().getName(), e.getDamager().getName());

                            if (timerMap.get(e.getEntity().getName()) != null) {
                                if (timerMap.get(e.getEntity().getName()) != 5) {
                                    timerMap.remove(e.getEntity().getName());
                                    timerMap.put(e.getEntity().getName(), 5);
                                }
                            }

                            entityTask(e.getEntity(), 5);
                        }
                    } else {
                        map.put(e.getEntity().getName(), arrowMap.get(e.getDamager().getEntityId()));

                        if (timerMap.get(e.getEntity().getName()) != null) {
                            if (timerMap.get(e.getEntity().getName()) != 6) {
                                timerMap.remove(e.getEntity().getName());
                                timerMap.put(e.getEntity().getName(), 6);
                            }
                        }

                        entityTask(e.getEntity(), 6);
                    }
                }
            }
        }
    }

    public static void entityTask(Entity e, int num){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (timerMap.get(e.getName()) != null) {
                    if (timerMap.get(e.getName()) == num) {
                        if (map.get(e.getName()) != null) {
                            map.remove(e.getName());
                        }
                        timerMap.remove(e.getName());
                    }
                }
            }
        }.runTaskLaterAsynchronously(CreditVoidKill.plugin ,CreditVoidKill.plugin.getConfig().getInt("Timer") * 20L);
    }

}
