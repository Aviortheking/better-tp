package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import net.DeltaWings.Minecraft.BetterTP.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
	private Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player && s.hasPermission("bettertp.home")) {
			Config c = new Config("data", s.getName());
			if(c.exist()) {
				if(a.length == 0) {
					if ( c.isSet("home") ) {
						String[] l = new String[2];
						l[0] = "home";
						l[1] = "home";
						Main.getInstance().tp(l, (Player) s);
						s.sendMessage(m.getString("home.teleported").replace("[home]", "home").replace("&", "§"));
						return true;
					} else {
						s.sendMessage(m.getString("home.dont-exist").replace("&", "§"));
						return true;
					}
				} else if(a.length == 1) {
					if ( c.isSet(a[0]) ) {
						String[] l = new String[2];
						l[0] = "home";
						l[1] = a[0];
						Main.getInstance().tp(l, (Player) s);
						s.sendMessage(m.getString("home.teleported").replace("[home]", a[0]).replace("&", "§"));
						return true;
					} else {
						s.sendMessage(m.getString("home.dont-exist").replace("&", "§"));
						return true;
					}
				}
			} else {
				s.sendMessage(m.getString("home.dont-exist"));
				return true;
			}
		}
		return false;
	}
}
