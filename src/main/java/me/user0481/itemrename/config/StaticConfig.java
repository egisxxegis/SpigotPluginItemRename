package me.user0481.itemrename.config;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StaticConfig implements Config{

    @Override
    public Material getGUIMaterialYes() {
        return Material.GREEN_WOOL;
    }

    @Override
    public Material getGUIMaterialNo() {
        return Material.RED_WOOL;
    }

    @Override
    public String getGUITitle() {
        return ChatColor.AQUA + "|" + ChatColor.DARK_GREEN + " " + ChatColor.RESET + "Keitimo lapuko naudojimas" + ChatColor.AQUA + " |";
    }

}
