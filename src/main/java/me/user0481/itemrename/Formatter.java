package me.user0481.itemrename;

import org.bukkit.ChatColor;

public class Formatter {
    public static String formatMessage(String message) {
        return ChatColor.DARK_GREEN + "[Lapukas] " + ChatColor.YELLOW + message;
    }

    public static String formatError(String message) {
        return ChatColor.DARK_GREEN + "[Lapukas] " + ChatColor.RED + message;
    }
}
