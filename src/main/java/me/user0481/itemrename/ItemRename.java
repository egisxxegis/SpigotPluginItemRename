package me.user0481.itemrename;

import me.user0481.itemrename.events.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.user0481.itemrename.commands.PervadintiCommand;

public final class ItemRename extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Item rename has started. Remember to bring your token.");
        getCommand("pervadinti").setExecutor(new PervadintiCommand());
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(),this);

    }


}
