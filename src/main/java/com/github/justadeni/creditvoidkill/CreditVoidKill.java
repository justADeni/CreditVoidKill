package com.github.justadeni.creditvoidkill;

import com.github.justadeni.creditvoidkill.command.Command;
import com.github.justadeni.creditvoidkill.command.TabComplete;
import com.github.justadeni.creditvoidkill.listeners.EntityDamage;
import com.github.justadeni.creditvoidkill.listeners.EntityShoot;
import com.github.justadeni.creditvoidkill.listeners.PlayerVoidDeath;
import com.github.justadeni.creditvoidkill.listeners.ProjectileHit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CreditVoidKill extends JavaPlugin {

    public static CreditVoidKill plugin = null;
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new EntityShoot(), this);
        getServer().getPluginManager().registerEvents(new PlayerVoidDeath(), this);
        getServer().getPluginManager().registerEvents(new ProjectileHit(), this);
        getCommand("creditvoidkill").setExecutor(new Command());
        getCommand("creditvoidkill").setTabCompleter(new TabComplete());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    //test commit
}
