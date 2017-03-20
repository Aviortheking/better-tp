package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Custom.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Homelist implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
		if(s instanceof Player) {
			for(String a : new Config("data", s.getName()).getSection("")) {
				s.sendMessage(("&b"+a).replace("&", "§"));
			}
		}
		return true;
	}
}