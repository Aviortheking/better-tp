package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;
import net.DeltaWings.Minecraft.BetterTP.Api.API;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homelist implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
		if(s instanceof Player) {
			Config c = new Config("", "messages");
			s.sendMessage(c.getString("help.top").replace("[help]", "Homelist").replace("&", "§"));
			for(String a : API.homelist(s.getName())) {
				s.sendMessage(("&4| &9").replace("&", "§") + a);
			}
			s.sendMessage(c.getString("help.bottom").replace("[help]", "Homelist").replace("&", "§"));
			return true;
		}
		return false;
	}
}
