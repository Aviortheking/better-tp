package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 19/03/2017 at.01:40
 */
public class Bettertp implements CommandExecutor {

	Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command d, String g, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.admin")) {
			Player p = (Player) s;
			if(a.length == 2) {
				if(a[0].equalsIgnoreCase("set")) {
					if(a[1].equalsIgnoreCase("spawn")) {
						Config c = new Config("data/spawn", "config");
						if(!c.exist()) c.create();
						Location l = p.getLocation();
						c.set("world", l.getWorld().getName());
						c.set("x", l.getX());
						c.set("y", l.getY());
						c.set("z", l.getZ());
						c.save();
						s.sendMessage(m.getString("spawn.set").replace("&","§"));
						return true;
					} else if(a[1].equalsIgnoreCase("lobby")) {
						Config c = new Config("data/lobby", "config");
						if(!c.exist()) c.create();
						Location l = p.getLocation();
						c.set("world", l.getWorld().getName());
						c.set("x", l.getX());
						c.set("y", l.getY());
						c.set("z", l.getZ());
						c.save();
						s.sendMessage(m.getString("lobby.set").replace("&","§"));
						return true;
					}
				} else if(a[0].equalsIgnoreCase("del") || a[0].equalsIgnoreCase("delete")) {
					if(a[1].equalsIgnoreCase("spawn")) {
						new Config("data/spawn", "config").delete();
						s.sendMessage(m.getString("spawn.deleted").replace("&","§"));
						return true;
					} else if(a[1].equalsIgnoreCase("lobby")) {
						new Config("data/lobby", "config").delete();
						s.sendMessage(m.getString("lobby.deleted").replace("&","§"));
						return true;
					}
				}
			} else if(a.length == 1) {
				if(a[0].equalsIgnoreCase("help")) {
					Config c = new Config("", "messages");
					p.sendMessage(c.getString("help.top").replace("&", "§"));
					p.sendMessage("&4| &9/btp &lset &llobby/spawn".replace("&", "§"));
					p.sendMessage("&4| &9To set spawn or lobby".replace("&", "§"));
					p.sendMessage("&4| &9/btp &ldel &llobby/spawn".replace("&", "§"));
					p.sendMessage("&4| &9To delete spawn/lobby".replace("&", "§"));
					p.sendMessage(c.getString("help.bottom").replace("&", "§"));
					return true;
				}
			}
		}
		return false;
	}
}
