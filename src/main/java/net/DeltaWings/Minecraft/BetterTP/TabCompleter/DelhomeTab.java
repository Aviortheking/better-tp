package net.DeltaWings.Minecraft.BetterTP.TabCompleter;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class DelhomeTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender s, Command u1, String unused, String[] a) {
		if( a.length == 1) {
			Config c = new Config("data", s.getName());
			return c.getSection("");
		} else return null;
	}
}
