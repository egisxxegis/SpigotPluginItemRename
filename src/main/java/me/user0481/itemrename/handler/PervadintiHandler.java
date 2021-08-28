package me.user0481.itemrename.handler;

import me.user0481.itemrename.config.Config;
import me.user0481.itemrename.config.ConfigFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PervadintiHandler {

    private final Player player;
    private final Config config = ConfigFactory.getConfig();
    private final static HashMap<Player, ItemStack> playerItemMap = new LinkedHashMap<>();
    private String lastError = "";

    public PervadintiHandler(Player player){

        this(player,false);
    }

    public PervadintiHandler(Player player, boolean register) {
        this.player = player;
        if (register)
            registerOriginalItem(player.getInventory().getItemInMainHand());

    }

    public boolean isItemValid(ItemStack chosenItem) {
        if (chosenItem == null || chosenItem.getType() == Material.AIR) {
            setLastError("Tuščios rankos negali pervadinti. Pasiimk kokį nors daiktą į ranką.");
            return false;
        }
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

    public ItemStack takePriceItem() {
        int index = findIndexOfPriceItem();
        if (index < 0) {
            setLastError("Tu neturi daikto " + config.getPriceItemTitle() + ChatColor.RED + ".");
            return new ItemStack(Material.AIR,1);
        }

        ItemStack priceItem = player.getInventory().getItem(index);
        if (priceItem.getAmount() > 1 ) {
            priceItem.setAmount(priceItem.getAmount() - 1);
            player.getInventory().setItem(index,priceItem);

        } else {
            player.getInventory().setItem(index,new ItemStack(Material.AIR));
        }
        priceItem.setAmount(1);
        return priceItem;

    }

    public ItemStack renameItem(ItemStack item, String[] arrayOfStrings) {
        return renameItem(item, String.join(" ",arrayOfStrings));
    }

    public ItemStack renameItem(ItemStack item, String name) {
        item = item.clone();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        item.setItemMeta(itemMeta);
        return item;
    }

    private void registerOriginalItem(ItemStack item) {
        playerItemMap.put(player,item);
//        System.out.println(Formatter.formatMessage("Registered " + player.getDisplayName()));
    }

    private void unregisterOriginalItem(){
        playerItemMap.remove(player);
//        System.out.println(Formatter.formatMessage("Unregistered " + player.getDisplayName()));
    }

    public void releaseHandler() {
        unregisterOriginalItem();
    }

    public ItemStack getOriginalItem() {
        return playerItemMap.get(player);
    }

    public boolean takeAwayOriginalItem() {
        ItemStack originalItem = getOriginalItem();
        if (!player.getInventory().contains(originalItem)) {
            System.out.println("Nerastas daiktas. type:" + originalItem.getType() + "; vardas: " + originalItem.getItemMeta().getDisplayName() + "; kiekis: " + originalItem.getAmount() + "; hashCode: " + originalItem.hashCode());
            setLastError("Kur pametei daiktą, kurį nori pervadinti?");
            return false;
        }
        if (player.getInventory().getItemInMainHand().getAmount() > 1) {
            setLastError("Pervadinimo metu gavai daiktų. Bandyk per naujo tą pačią komandą.");
            return false;
        }
        int heldItemIndex = player.getInventory().getHeldItemSlot();
        player.getInventory().setItem(heldItemIndex,new ItemStack(Material.AIR));
        if (player.getInventory().getItemInMainHand().equals(originalItem)) {
            setLastError("Nepavyko pervadinti - seno daikto neradau.");
            return false;
        }
        return true;
    }

    public void giveNewItem(ItemStack item) {
        player.getInventory().addItem(item);
    }

    public String getLastError() {
        return lastError;
    }

    private void setLastError(String toWhat) {
        this.lastError = toWhat;
    }

    public boolean isItemNameOfValidLength(String[] args) {
        return isItemNameOfValidLength(String.join(" ",args));
    }

    public boolean isItemNameOfValidLength(String name) {
        int length = ConfigFactory.getConfig().getItemMaxAllowedLength();
        if (name != null && (name.length() > length)) {
            setLastError("Norimas item vardas viršijo " + length + " simbolių limitą. Tavo duoto item vardo ilgis buvo " + name.length() + ".");
            return false;
        }
        return true;
    }

}
