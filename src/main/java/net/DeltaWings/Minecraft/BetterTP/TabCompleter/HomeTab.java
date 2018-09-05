package net.DeltaWings.Minecraft.BetterTP.TabCompleter;

import net.DeltaWings.Minecraft.BetterTP.Api.API;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class HomeTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender s, Command u1, String unused, String[] a) {
		if( a.length == 1) {
			return API.homelist(s.getName());
		} else return null;
	}
}
