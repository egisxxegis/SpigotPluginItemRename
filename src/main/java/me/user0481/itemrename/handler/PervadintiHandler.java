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
        if (chosenItem.getItemMeta().getDisplayName().equals(config.getPriceItemTitle())) {
            setLastError("Pervadinti šito daikto negali. Paimk į ranką kitą daiktą.");
            return false;
        }
        return true;
    }

    private ItemStack getPriceItem() {
        ItemStack priceItem = new ItemStack(config.getPriceItemMaterial());
        ItemMeta piMeta = priceItem.getItemMeta();
        piMeta.setDisplayName(config.getPriceItemTitle());
        priceItem.setItemMeta(piMeta);
        return priceItem;
    }

    private int findIndexOfPriceItem() {
        int toReturn = -1;
        ItemStack[] items = player.getInventory().getStorageContents();
        for (int i=0; i<items.length; i++) {
            ItemStack item = items[i];
            if (item == null)
                continue;
            if (item.getType() != config.getPriceItemMaterial())
                continue;
            if (item.getItemMeta().getDisplayName().equals(config.getPriceItemTitle())) {
                return i;
            }
        }
        return toReturn;
    }

    public boolean hasPriceItem() {

        int index = findIndexOfPriceItem();

        if (index < 0) {
            setLastError("Tu neturi daikto " + config.getPriceItemTitle() + ChatColor.RED + ".");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean takePriceItem() {
        int index = findIndexOfPriceItem();
        if (index < 0) {
            setLastError("Tu neturi daikto " + config.getPriceItemTitle() + ChatColor.RED + ".");
            return false;
        }

        ItemStack priceItem = player.getInventory().getItem(index);
        if (priceItem.getAmount() > 1 ) {
            priceItem.setAmount(priceItem.getAmount() - 1);
            player.getInventory().setItem(index,priceItem);
            return true;
        } else {
            player.getInventory().setItem(index,new ItemStack(Material.AIR));
            return true;
        }

    }

    public String getLastError() {
        return lastError;
    }

    private void setLastError(String toWhat) {
        this.lastError = toWhat;
    }

}
