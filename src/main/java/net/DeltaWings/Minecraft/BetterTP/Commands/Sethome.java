package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 19/03/2017 at.01:37
 */
public class Sethome implements CommandExecutor {

	Config m = new Config("", "messages");
	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.sethome")) {
			if(a.length == 0) {
				Config c = new Config("data", s.getName());
				if(!c.exist()) c.create();
				Location l = ((Player) s).getLocation();
				c.set("home.world", l.getWorld().getName());
				c.set("home.x", l.getX());
				c.set("home.y", l.getY());
				c.set("home.z", l.getZ());
				c.save();
				s.sendMessage(m.getString("home.set").replace("[home]", "home").replace("&", "§"));
				return true;
			} else if(a.length == 1) {
				Config c = new Config("data", s.getName());
				if(!c.exist()) c.create();
				Location l = ((Player) s).getLocation();
				c.set(a[0] + ".world", l.getWorld().getName());
				c.set(a[0] + ".x", l.getX());
				c.set(a[0] + ".y", l.getY());
				c.set(a[0] + ".z", l.getZ());
				c.save();
				s.sendMessage(m.getString("home.set").replace("[home]", a[0]).replace("&", "§"));
				return true;
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
