package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
	private final Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.home")) {
			Config c = new Config("data", s.getName());
			if(c.exist()) {
				if(a.length == 0) {
					if ( c.isSet("home") ) {
						String t = "home.";
						((Player) s).teleport(new Location(Bukkit.getServer().getWorld(c.getString(t+"world")), c.getDouble(t+"x"), c.getDouble(t+"y"), c.getDouble(t+"z")));
						s.sendMessage(m.getString("home.teleported").replace("[home]", "home").replace("&", "§"));
						return true;
					} else {
						s.sendMessage(m.getString("home.dont-exist").replace("&", "§").replace("[home]", "Home"));
						return true;
					}
				} else if(a.length == 1) {
					if ( c.isSet(a[0]) ) {
						String t = a[0] + ".";
						((Player) s).teleport(new Location(Bukkit.getServer().getWorld(c.getString(t+"world")), c.getDouble(t+"x"), c.getDouble(t+"y"), c.getDouble(t+"z")));

						s.sendMessage(m.getString("home.teleported").replace("[home]", a[0]).replace("&", "§"));
						return true;
					} else {
						s.sendMessage(m.getString("home.dont-exist").replace("&", "§").replace("[home]", a[0]));
						return true;
					}
				}
			} else {
				s.sendMessage(m.getString("home.dont-exist"));
				return true;
			}
		} else if(s instanceof Player && !s.hasPermission("bettertp.home")) {
			s.sendMessage(m.getString("global.permission").replace("&", "§"));
			return true;
		} else {
			s.sendMessage(m.getString("global.not-console"));
			return true;
		}
		return false;
	}
}
