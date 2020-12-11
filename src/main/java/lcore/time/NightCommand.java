package lcore.time;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import lcore.Managers.ConfigValues;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission(ConfigValues.nightPerm)) {
            World w = p.getWorld();
            if (args.length == 0) {

                w.setTime(13000);
                p.playSound(p.getLocation(),ConfigValues.successSound,1,1);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',ConfigValues.nightCommand.replace("%world%",p.getWorld().getName())));

            } else if (Bukkit.getWorld(args[0]) != null) {

                Bukkit.getWorld(args[0]).setTime(13000);
                p.playSound(p.getLocation(),ConfigValues.successSound,1,1);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',ConfigValues.nightCommand.replace("%world%",args[0])));

            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',ConfigValues.unknownworld.replace("%world%",args[0])));
                p.playSound(p.getLocation(), Sound.valueOf(ConfigValues.failSound), 1, 1);
                StringBuilder ws = new StringBuilder();
                for (World wss : Bukkit.getWorlds()){
                    if (ws.length() > 0) ws.append(", ");
                    ws.append(wss.getName());
                } p.sendMessage(ChatColor.translateAlternateColorCodes('&',ConfigValues.availableWorlds.replace("%worlds%",ws.toString())));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',ConfigValues.noperm));
            p.playSound(p.getLocation(), Sound.valueOf(ConfigValues.failSound), 1, 1);
        }

        return false;
    }
}
