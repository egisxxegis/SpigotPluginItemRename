package me.user0481.itemrename;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import me.user0481.itemrename.commands.Pervadinti;

public final class ItemRename extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Item rename has started. Remember to bring your token.");
        getCommand("pervadinti").setExecutor(new Pervadinti());

    }


}
