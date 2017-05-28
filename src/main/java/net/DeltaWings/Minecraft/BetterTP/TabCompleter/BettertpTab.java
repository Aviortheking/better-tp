package net.DeltaWings.Minecraft.BetterTP.TabCompleter;

import net.DeltaWings.Minecraft.BetterTP.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BettertpTab implements TabCompleter {

	private final String[] menu = new String[]{"set", "del", "delete", "help", "config"}, setdel = new String[]{"spawn","lobby"}, confopt = new String[]{""};

	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String unused, String[] a) {
		Main.debug("Tab Completing c : "+ c.getName() +" + \nNa : " + a.length);
		for (String b: a) Main.debug(b);
		if(a.length > 0 && a[0].equals("")) {
			return Arrays.asList(menu);
		}
		if(a.length == 1) {
			if(!a[0].equals("")) {
				List<String> l = new ArrayList<>();
				for (String t: menu) if(t.startsWith(a[0].toLowerCase())) l.add(t);
				return l;
			}
		} else if(a.length == 2) {
			String[] y;
			if (a[0].equalsIgnoreCase("set") || a[0].equalsIgnoreCase("del") || a[0].equalsIgnoreCase("delete")) y = setdel;
			else if (a[0].equalsIgnoreCase("config")) y = confopt;
			else return null;
			if (!a[1].equals("")) {
				List<String> l = new ArrayList<>();
				for (String t : y) if (t.startsWith(a[1].toLowerCase())) l.add(t);
				return l;
			} else return Arrays.asList(y);
		}
		return null;
	}
}
