package me.robomwm.DimensionTeleporter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
                sender.sendMessage("/dt <world name>");
            if (!(sender instanceof Player))
            {
                sender.sendMessage("Use /dtp <world name> <player>");
            }
            else
            {
                Player player = (Player)sender;
                World dest = Bukkit.getWorld(args[0]);
                Location spawn = dest.getSpawnLocation();
                player.teleport(spawn);
            }
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("dtp"))
        {
            if (args.length < 2)
                sender.sendMessage("/dtp <world name> <player>");
            Player player = (Bukkit.getPlayer(args[1]));
            if (player == null)
            {
                sender.sendMessage(args[0] + " is not online!");
                return false;
            }
            World dest = Bukkit.getWorld(args[0]);
            Location spawn = dest.getSpawnLocation();
            player.teleport(spawn);
        }
        return false;
    }
}
