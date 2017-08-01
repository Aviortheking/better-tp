package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Bettertp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command unu2, String unu1, String[] a) {
		Config m = new Config("", "messages");
		Config g = new Config("", "config");
		if(s instanceof Player && s.hasPermission("bettertp.admin")) {
			Player p = (Player) s;
			if(a.length == 2) {
				if(a[0].equalsIgnoreCase("set")) {
					if(a[1].equalsIgnoreCase("spawn")) {
						Config c = null;
						if(g.getString("spawn.work").equalsIgnoreCase("server")) c = new Config("data/spawn", "config");
						else if(g.getString("spawn.work").equalsIgnoreCase("world")) c = new Config("data/spawn", ((Player) s).getWorld().getName());
						if(c != null && !c.exist()) try {
							c.create();
						} catch ( IOException e ) {
							e.printStackTrace();
							s.sendMessage("Error please call an administrator !");
						}
						Location l = p.getLocation();
						c.set("world", l.getWorld().getName());
						c.set("x", l.getX());
						c.set("y", l.getY());
						c.set("z", l.getZ());
						try {
							c.save();
						} catch ( IOException e ) {
							e.printStackTrace();
							s.sendMessage("Error please call an administrator !");
						}
						s.sendMessage(m.getString("spawn.set").replace("&","§"));

						return true;
					} else if(a[1].equalsIgnoreCase("lobby")) {
						Config c = new Config("data/lobby", "config");
						if(!c.exist()) try {
							c.create();
						} catch (IOException e) {
							e.printStackTrace();
							s.sendMessage("Error Please call an administrator !");
						}
						Location l = p.getLocation();
						c.set("world", l.getWorld().getName());
						c.set("x", l.getX());
						c.set("y", l.getY());
						c.set("z", l.getZ());
						try {
							c.save();
						} catch ( IOException e ) {
							e.printStackTrace();
							s.sendMessage("Error Please call an administrator !");
						}
						s.sendMessage(m.getString("lobby.set").replace("&","§"));
						return true;
					}
				} else if(a[0].equalsIgnoreCase("del") || a[0].equalsIgnoreCase("delete")) {
					if(a[1].equalsIgnoreCase("spawn")) {
						if(g.getString("spawn.work").equalsIgnoreCase("server")) new Config("data/spawn", "config").delete();
						else if(g.getString("spawn.work").equalsIgnoreCase("world")) new Config("data/spawn", ((Player) s).getWorld().getName()).delete();
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
					p.sendMessage(c.getString("help.top").replace("[help]", "BetterTP").replace("&", "§"));
					p.sendMessage("&4| &9/btp &lset &llobby/spawn".replace("&", "§"));
					p.sendMessage("&4| &9To set spawn or lobby".replace("&", "§"));
					p.sendMessage("&4| &9/btp &ldel &llobby/spawn".replace("&", "§"));
					p.sendMessage("&4| &9To delete spawn/lobby".replace("&", "§"));
					p.sendMessage(c.getString("help.bottom").replace("[help]", "BetterTP").replace("&", "§"));
					return true;
				}
			}
		} else if(s instanceof Player){
			s.sendMessage(m.getString("global.permission").replace("&", "§"));
			return true;
		} else {
			s.sendMessage(m.getString("global.not-console"));
		}
		return false;
	}
}
