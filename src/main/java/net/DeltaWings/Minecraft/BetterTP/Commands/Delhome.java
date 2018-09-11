package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Delhome implements CommandExecutor {

	private final Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.delhome")) {
			if(a.length > 2) return false; //too many arguments
			Config c = new Config(API.getPlayersFolder(), s.getName());
			String homename = a.length == 0 ? "home" : a[0];
			try {
				if(c.exist() && c.isSet(homename)) {
					c.set(homename, null);
					if(c.getSection("").size() == 0) {
						c.delete();
					} else c.save();
					//sendmessage home deleted
				} else {
					//sendmessage home don't exist
				}
			} catch (Exception e) {
				e.printStackTrace();
				//sendmessage error happened
			}
			return true;
		} else if(s instanceof ConsoleCommandSender) {
			//sendmessage console can't use this command
			return true;
		} else {
			//sendmessage missing permission
			return true;
		}
	}
}
