package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
	private final Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command unu1, String unu2, String[] unu3) {
		if(s instanceof Player ) {
			if(s.hasPermission("bettertp.lobby")) {
				Config c = new Config("data/lobby", "config");
				if(c.exist()) {
					Double y = c.getDouble("x", (double) -1);
					if(y != -1) {
						((Player) s).teleport(new Location(Bukkit.getServer().getWorld(c.getString("world")), c.getDouble("x"), c.getDouble("y"), c.getDouble("z")));
						s.sendMessage(m.getString("lobby.teleported").replace("&", "§"));
						return true;
					}
				} else {
					s.sendMessage(m.getString("lobby.not-set").replace("&", "§"));
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
		return false;
	}
}
