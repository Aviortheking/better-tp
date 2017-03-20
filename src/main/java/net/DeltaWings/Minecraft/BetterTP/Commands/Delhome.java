package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 19/03/2017 at.01:37
 */
public class Delhome implements CommandExecutor {

	private Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.delhome")) {
			Config c = new Config("data", s.getName());
			if(a.length == 1) {
				if(c.isSet(a[0])) {
					c.set(a[0], null);
					c.save();
					s.sendMessage(m.getString("home.deleted"));
					return true;
				} else {
					s.sendMessage(m.getString("home.dont-exist").replace("[home]", a[0]).replace("&", "§"));
					return true;
				}
			} else if(a.length == 0) {
				if(c.isSet("home")) {
					c.set("home", null);
					c.save();
					s.sendMessage(m.getString("home.deleted"));
					return true;
				} else {
					s.sendMessage(m.getString("home.dont-exist").replace("[home]", "home").replace("&", "§"));
					return true;
				}
			}
		} else if(s instanceof Player && !s.hasPermission("bettertp.delhome")) {
			s.sendMessage(m.getString("global.permission").replace("&", "§"));
			return true;
		} else {
			s.sendMessage("global.not-console");
			return true;
		}
		return false;
	}
}
