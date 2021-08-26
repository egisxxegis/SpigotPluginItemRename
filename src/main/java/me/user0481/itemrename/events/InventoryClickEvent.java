package me.user0481.itemrename.events;

import me.user0481.itemrename.Formatter;
import me.user0481.itemrename.config.Config;
import me.user0481.itemrename.config.ConfigFactory;
import me.user0481.itemrename.handler.PervadintiHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void InventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent e) {
        Config config = ConfigFactory.getConfig();
        Inventory clickedInventory = e.getClickedInventory();
        if(clickedInventory == null
                || !e.getView().getTitle().equalsIgnoreCase(config.getGUITitle()))
            return;

        ItemStack item = e.getCurrentItem();
        if (item == null)
            return;

        Player player = (Player) e.getWhoClicked();

        e.setCancelled(true);
        if (item.getType() == config.getGUINoMaterial()) {
            player.closeInventory();
            player.sendMessage(Formatter.formatMessage(ChatColor.RED + "Pervadinimas atšauktas."));
        } else if (item.getType() == config.getGUIYesMaterial()) {
            player.closeInventory();
            PervadintiHandler handler = new PervadintiHandler(player);
            if (!handler.hasPriceItem()) {
                player.sendMessage(Formatter.formatError(handler.getLastError()));
                return ;
            }
            //remove keitimo lapukas from inventory
            //remove item from inventory
            e.getWhoClicked().sendMessage(Formatter.formatMessage(ChatColor.RED + "Pervadinimas vykdomas."));
        }

    }

}
