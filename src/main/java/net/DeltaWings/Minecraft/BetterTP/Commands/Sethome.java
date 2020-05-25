package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Sethome implements CommandExecutor {

	private final Config m = new Config("", "messages");
	private final Config mc = new Config("", "config");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] a) {
		if(s instanceof Player/* && s.hasPermission("bettertp.sethome")*/) {
			if(a.length > 2) return false; //too many arguments
			Config c = new Config(API.getPlayersFolder(), s.getName());
			Integer maxhomes = -1;
			for(String e : mc.getSection("maxhomes")) {
				if(true || s.hasPermission("bettertp.max."+e)) {
					Integer max = mc.getInt("maxhomes."+e, 0);
					maxhomes = max > maxhomes ? max : maxhomes; //if max > maxhome then maxhome = max
				}
			}
			if(c.getSection("").size() >= maxhomes) { //Player already have max number of home
				s.sendMessage(m.getString("home.max").replace("[max]", c.getSection("").size() + "").replace("&", "§"));
				return true;
			} else { //player can have more homes
				try {
					if(!c.exist()) c.create();
					String homename = a.length == 0 ? "home" : a[0];
					if(c.isSet(homename)) {
						s.sendMessage(m.getString("home.max").replace("[max]", homename).replace("&", "§"));
					} else {
						Location l = ((Player) s).getLocation();
						c.set(homename+".world", l.getWorld().getName());
						c.set(homename+".x", l.getX());
						c.set(homename+".y", l.getY());
						c.set(homename+".z", l.getZ());
						c.save();
						s.sendMessage(m.getString("home.set").replace("[home]", homename).replace("&", "§"));
					}
				} catch ( IOException e ) {
					e.printStackTrace();
					s.sendMessage("Error, Please call an Admin !");
				}
				return true;
			}
		} else if(s instanceof ConsoleCommandSender) {
			//sendmessage can't be used as the console
			return true;
		} else {
			//sendmessage don't have the permission
			return true;
		}
	}
}
