package me.user0481.itemrename.handler;

import me.user0481.itemrename.config.Config;
import me.user0481.itemrename.config.ConfigFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PervadintiHandler {

    private final Player player;
    private final Config config = ConfigFactory.getConfig();
    private String lastError = "";

    public PervadintiHandler(Player player){
        this.player = player;
    }

    public boolean isItemValid(ItemStack chosenItem) {
        if (chosenItem.getAmount() > 1) {
            setLastError("Pervadinti gali tik vieną daiktą. Norėjai pervadinti " + chosenItem.getAmount() + " daiktus.");
            return false;
        }
        return true;
    }

    private ItemStack getPriceItem() {
        ItemStack priceItem = new ItemStack(Material.PAPER);
        ItemMeta piMeta = priceItem.getItemMeta();
        piMeta.setDisplayName(config.getPriceItemTitle());
        priceItem.setItemMeta(piMeta);
        return priceItem;
    }

    public boolean hasPriceItem() {

        ItemStack priceItem = getPriceItem();

        if (player.getInventory().contains(priceItem)) {
            return true;
        }
        else {
            setLastError("Tu neturi daikto " + config.getPriceItemTitle() + ChatColor.RED + ".");
            return false;
        }
    }

    public String getLastError() {
        return lastError;
    }

    private void setLastError(String toWhat) {
        this.lastError = toWhat;
    }

}
