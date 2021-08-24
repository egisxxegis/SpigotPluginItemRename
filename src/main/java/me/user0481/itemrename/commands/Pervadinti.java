package me.user0481.itemrename.commands;

import me.user0481.itemrename.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Pervadinti implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println(Formatter.formatMessage("/pervadinti gali naudoti tik žaidėjai.") );
            return false;
        }

        Player player = (Player) sender;
        if (player.hasPermission("ItemRename.pervadinti") )
            player.sendMessage(Formatter.formatMessage("Panaudojai keitimo lapukų komandą.") );
        else {
            player.sendMessage(Formatter.formatMessage("Neturi permission'ų naudotis keitimo lapukų komanda."));
            return true;
        }

        Inventory dialogInventory = Bukkit.createInventory(player,9,"Keitimo lapuko naudojimas");

        player.openInventory(dialogInventory);

        return true;
    }
}
