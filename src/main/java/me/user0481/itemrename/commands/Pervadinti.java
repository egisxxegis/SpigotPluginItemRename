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

        Inventory dialogInventory   = Bukkit.createInventory(player,9,"Keitimo lapuko naudojimas");
        ItemStack dialogYes         = new ItemStack(Material.GREEN_WOOL,1);
        ItemStack dialogNo          = new ItemStack(Material.RED_WOOL,1);

        for(int i=0; i<=3; dialogInventory.setItem(i++,dialogYes));
        dialogInventory.setItem(4,player.getInventory().getItemInMainHand());
        for(int i=5; i<=8; dialogInventory.setItem(i++,dialogNo));


        player.openInventory(dialogInventory);

        return true;
    }
}
