package me.user0481.itemrename.events;

import me.user0481.itemrename.Formatter;
import me.user0481.itemrename.config.Config;
import me.user0481.itemrename.config.ConfigFactory;
import me.user0481.itemrename.handler.PervadintiHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class InventoryCloseEvent implements Listener {

    @EventHandler
    public void InventoryCloseEvent(org.bukkit.event.inventory.InventoryCloseEvent e) {
        Config config = ConfigFactory.getConfig();
        if(!e.getView().getTitle().equalsIgnoreCase(config.getGUITitle()))
            return;

        Player player = (Player) e.getPlayer();
        PervadintiHandler handler = new PervadintiHandler(player);
        handler.releaseHandler();

    }
}
