package me.user0481.itemrename.commands;

import me.user0481.itemrename.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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

        ItemMeta yesMeta = dialogYes.getItemMeta();
        yesMeta.setDisplayName(ChatColor.DARK_GREEN + "Pirkti");
        ArrayList<String> yesLore = new ArrayList<>();
        yesLore.add(ChatColor.GOLD + "Pirkti daikto");
        yesLore.add(ChatColor.GOLD + "pervadinimą.");
        yesLore.add(ChatColor.GOLD + "Kaina: " + ChatColor.RED + "1 Keitimo lapelis" + ChatColor.GOLD + ".");
        yesMeta.setLore(yesLore);
        dialogYes.setItemMeta(yesMeta);

        ItemMeta noMeta = dialogNo.getItemMeta();
        noMeta.setDisplayName(ChatColor.RED + "Netinka");
        ArrayList<String> noLore = new ArrayList<>();
        noLore.add(ChatColor.GOLD + "Atšaukti daikto");
        noLore.add(ChatColor.GOLD + "pervadinimą.");
        noLore.add(ChatColor.GOLD + "Keitimo lapelis nebus");
        noLore.add(ChatColor.GOLD + "panaudotas");
        noMeta.setLore(noLore);
        dialogNo.setItemMeta(noMeta);

        int mainItemIndex = 4;
        for(int i=0; i<=mainItemIndex-1; dialogInventory.setItem(i++,dialogYes));
        dialogInventory.setItem(mainItemIndex,player.getInventory().getItemInMainHand());
        for(int i=mainItemIndex+1; i<=8; dialogInventory.setItem(i++,dialogNo));


        player.openInventory(dialogInventory);

        return true;
    }
}
