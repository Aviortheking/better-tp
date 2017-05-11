package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Sethome implements CommandExecutor {

	private final Config m = new Config("", "messages");
	private final Config mc = new Config("", "config");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.sethome")) {
			Config c = new Config("data", s.getName());
			ArrayList<String> r = new ArrayList<>();
			for(String e : mc.getSection("maxhomes")) {
				if(s.hasPermission("bettertp.max."+e)) r.add(e);
			}
			Boolean p = false;
			for(String e : r) {
				if(c.getSection("").size() == mc.getInt("maxhomes." + e)) p = true;
			}
			if(p) {
				s.sendMessage(m.getString("home.max").replace("[max]", c.getSection("").size() + "").replace("&", "§"));
				return true;
			} else {
				if(!c.exist()) try {
					c.create();
				} catch ( IOException e ) {
					e.printStackTrace();
					s.sendMessage("Error, Please call an Admin !");
				}
				if(a.length == 0) {
					Location l = ((Player) s).getLocation();
					c.set("home.world", l.getWorld().getName());
					c.set("home.x", l.getX());
					c.set("home.y", l.getY());
					c.set("home.z", l.getZ());
					try {
						c.save();
					} catch ( IOException e ) {
						e.printStackTrace();
						s.sendMessage("Error, Please call an Admin !");
					}
					s.sendMessage(m.getString("home.set").replace("[home]", "home").replace("&", "§"));
					return true;
				} else if(a.length == 1) {
					Location l = ((Player) s).getLocation();
					c.set(a[0] + ".world", l.getWorld().getName());
					c.set(a[0] + ".x", l.getX());
					c.set(a[0] + ".y", l.getY());
					c.set(a[0] + ".z", l.getZ());
					try {
						c.save();
					} catch ( IOException e ) {
						e.printStackTrace();
						s.sendMessage("Error, Please call an Admin !");
					}
					s.sendMessage(m.getString("home.set").replace("[home]", a[0]).replace("&", "§"));
					return true;
				}
			}
		} else if(s instanceof Player && !s.hasPermission("bettertp.sethome")) {
			s.sendMessage(m.getString("global.permission").replace("&", "§"));
			return true;
		} else {
			s.sendMessage(m.getString("global.not-console"));
			return true;
		}
		return false;
	}
}
