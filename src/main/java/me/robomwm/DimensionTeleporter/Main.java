package me.robomwm.DimensionTeleporter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ListIterator;

/**
 * Created by Robo on 11/21/2015.
 */
public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("dt"))
        {
            if (args.length < 1)
            {
                sender.sendMessage("Loaded worlds:");
                ListIterator<World> worlds = Bukkit.getWorlds().listIterator();
                while (worlds.hasNext())
                    sender.sendMessage(worlds.next().getName());
                return false;
            }

            if (!(sender instanceof Player))
            {
                sender.sendMessage("Use /dtp <world name> <player>");
            }
            else
            {
                Player player = (Player)sender;
//                if (!player.hasPermission("DimensionTeleporter.dt"))
//                {
//                    sender.sendMessage("You do not have the DimensionTeleporter.dt permission node");
//                    return true;
//                }
                World dest = Bukkit.getWorld(args[0]);
                if (dest == null)
                {
                    sender.sendMessage("Loaded worlds:");
                    ListIterator<World> worlds = Bukkit.getWorlds().listIterator();
                    while (worlds.hasNext())
                        sender.sendMessage(worlds.next().getName());
                    sender.sendMessage(ChatColor.RED + "World \"" + args[0] + "\" is not loaded.");
                    return false;
                }
                Location spawn = dest.getSpawnLocation();
                player.teleport(spawn);
            }
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("dtp"))
        {
            if (args.length < 2)
            {
                sender.sendMessage(ChatColor.YELLOW + "Loaded worlds:");
                ListIterator<World> worlds = Bukkit.getWorlds().listIterator();
                while (worlds.hasNext())
                    sender.sendMessage(ChatColor.GOLD + worlds.next().getName());
                return false;
            }
//            if ((sender instanceof Player))
//            {
//                Player p = (Player)sender;
//                if (!p.hasPermission("DimensionTeleporter.dtp"))
//                {
//                    sender.sendMessage("You do not have the DimensionTeleporter.dtp permission node");
//                    return true;
//                }
//            }
            Player player = (Bukkit.getPlayer(args[1]));
            if (player == null)
            {
                sender.sendMessage(ChatColor.RED + "Player \"" + args[1] + "\" is not online.");
                return false;
            }
            World dest = Bukkit.getWorld(args[0]);
            if (dest == null)
            {
                sender.sendMessage("Loaded worlds:");
                ListIterator<World> worlds = Bukkit.getWorlds().listIterator();
                while (worlds.hasNext())
                    sender.sendMessage(worlds.next().getName());
                sender.sendMessage(ChatColor.RED + "World \"" + args[0] + "\" is not loaded.");
                return false;
            }
            Location spawn = dest.getSpawnLocation();
            player.teleport(spawn);
            sender.sendMessage(ChatColor.GREEN + "Teleported " + player.getName() + " to " + dest.getName());
            return true;
        }
        return false;
    }
}
