package me.user0481.itemrename.config;

import org.bukkit.Material;

public class StaticConfig implements Config{

    public Material getGUIMaterialYes() {
        return Material.GREEN_WOOL;
    }

    public Material getGUIMaterialNo() {
        return Material.RED_WOOL;
    }

}
