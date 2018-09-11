package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {

	private final Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.home")) {
			if(a.length > 1) return false; //too many arguments
			Config c = new Config(API.getPlayersFolder(), s.getName());
			String homename = a.length == 0 ? "home" : a[0];
			if(c.isSet(homename)) {
				((Player) s).teleport(new Location(Bukkit.getServer().getWorld(c.getString(homename+".world")), c.getDouble(homename+".x"), c.getDouble(homename+".y"), c.getDouble(homename+".z")));
				//sendmessage teleported to home home
				return true;
			} else {
				//sendmessage home don't exist
				return true;
			}
		} else if(s instanceof ConsoleCommandSender) {
			//sendmessage console can't use that command
			return true;
		} else {
			//sendmessage you don't have the permission
			return true;
		}
	}
}

// /home [home]