package me.user0481.itemrename.config;

import org.bukkit.ChatColor;
import org.bukkit.Material;

class StaticConfig implements Config{

    @Override
    public Material getGUIYesMaterial() {
        return Material.GREEN_WOOL;
    }

    @Override
    public Material getGUINoMaterial() {
        return Material.RED_WOOL;
    }

    @Override
    public String getGUITitle() {
        return ChatColor.AQUA + "|" + ChatColor.DARK_GREEN + " " + ChatColor.RESET + "Keitimo lapuko naudojimas" + ChatColor.AQUA + " |";
    }

    @Override
    public int getGUIMainItemIndex() {
        return 4;
    }

    @Override
    public Material getPriceItemMaterial() {
        return Material.PAPER;
    }

    @Override
    public String getPriceItemTitle() {
        return ChatColor.GOLD + "Keitimo Lapukas";
    }

}
