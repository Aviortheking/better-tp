package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command d, String l, String[] a) {
		Config m = new Config("", "messages");
		if(s instanceof Player) {
			if(s.hasPermission("bettertp.spawn")) {
				Config g = new Config("", "config");
				Config c = null;
				if(g.getString("spawn.work").equalsIgnoreCase("server")) c = new Config("data/spawn", "config");
				else if(g.getString("spawn.work").equalsIgnoreCase("world")) c = new Config("data/spawn", ((Player) s).getWorld().getName());
				if(c != null && c.exist()) {
					((Player) s).teleport(new Location(Bukkit.getServer().getWorld(c.getString("world")), c.getDouble("x"), c.getDouble("y"), c.getDouble("z")));
					s.sendMessage(m.getString("spawn.teleported").replace("&", "§"));
					return true;
				} else {
					s.sendMessage(m.getString("spawn.not-set").replace("&", "§"));
					return true;
				}
			} else {
				s.sendMessage(m.getString("global.permission").replace("&", "§"));
				return true;
			}
		} else {
			s.sendMessage(m.getString("global.not-console"));
			return true;
		}
	}
}
