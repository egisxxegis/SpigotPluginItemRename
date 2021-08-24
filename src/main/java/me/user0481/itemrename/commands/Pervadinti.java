package me.user0481.itemrename.commands;

import me.user0481.itemrename.Formatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pervadinti implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println(Formatter.formatMessage("/pervadinti gali naudoti tik žaidėjai.") );
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage(Formatter.formatMessage("You have have issued item rename command.") );

        return true;
    }
}
