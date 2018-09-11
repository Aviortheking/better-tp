package net.DeltaWings.Minecraft.BetterTP.TabCompleter;

import net.DeltaWings.Minecraft.BetterTP.Api.API;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class DelhomeTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender s, Command u1, String unused, String[] a) {
		if( a.length == 1) {
			if(a[0] == "") {
				return API.homelist(s.getName());
			} else {
				ArrayList<String> t = new ArrayList<>();
				for (String home : API.homelist(s.getName())) {
					if(home.startsWith(a[0].toLowerCase())) t.add(home);
				}
				return t;
			}
			
		} else return null;
	}
}
