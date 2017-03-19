package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import net.DeltaWings.Minecraft.BetterTP.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
	Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command abdoulila, String l, String[] a) {
		if(s instanceof Player) {
			if(s.hasPermission("bettertp.spawn")) {
				Config c = new Config("data/spawn", "config");
				if(c.exist()) {
					Double y = c.getDouble("x", (double) -1);
					if(!(y == -1)) {
						String[] sl = new String[2];
						sl[0] = "spawn";
						Main.getInstance().tp(sl, (Player) s );
					}
				}
			} else s.sendMessage(m.getString("global.permission"));
		} else {
			s.sendMessage(m.getString("global.not-console"));
		}
		return false;
	}
}
