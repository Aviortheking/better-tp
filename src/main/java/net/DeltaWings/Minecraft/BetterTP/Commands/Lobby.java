package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;
import net.DeltaWings.Minecraft.BetterTP.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 18/03/2017 at.15:24
 */
public class Lobby implements CommandExecutor {
	private Config m = new Config("", "messages");

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
		if(s instanceof Player ) {
			if(s.hasPermission("bettertp.lobby")) {
				Config c = new Config("data/lobby", "config");
				if(c.exist()) {
					Double y = c.getDouble("x", (double) -1);
					if(!(y == -1)) {
						String[] sl = new String[2];
						sl[0] = "lobby";
						Main.getInstance().tp(sl, (Player) s );
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
