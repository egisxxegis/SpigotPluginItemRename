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
        PervadintiHandler handler = new PervadintiHandler(player);

        //NO
        if (item.getType() == config.getGUINoMaterial()) {
            player.closeInventory();
            player.sendMessage(Formatter.formatError("Pervadinimas atšauktas."));
            handler.releaseHandler();

        //YES
        } else if (item.getType() == config.getGUIYesMaterial()) {
            player.closeInventory();
            handler.releaseHandler();
            if (!handler.takePriceItem()) {
                player.sendMessage(Formatter.formatError(handler.getLastError()));
                return ;
            }
            if (!handler.takeAwayOriginalItem()) {
                player.sendMessage(Formatter.formatError(handler.getLastError()));
                //return back priceItem
                return;
            }
            handler.giveNewItem(e.getClickedInventory().getItem(config.getGUIMainItemIndex()));
            player.sendMessage(Formatter.formatMessage("Daiktas pervadintas."));
        }

    }

}
