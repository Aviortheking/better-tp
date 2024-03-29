package net.DeltaWings.Minecraft.BetterTP.TabCompleter;

import net.DeltaWings.Minecraft.BetterTP.Main;
import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;
import net.DeltaWings.Minecraft.BetterTP.Libs.FileManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BettertpTab implements TabCompleter {

	private final String[]
		menu = new String[]{"set", "del", "delete", "help", "home", "delhome"},
		setdel = new String[]{"spawn","lobby"},
		confopt = new String[]{""};

	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String unused, String[] a) {
		Main.debug("Tab Completing c : "+ c.getName() +" + \na length: " + a.length);
		Main.debug("a : " + a.toString());
		if(a.length == 1) { // no args
			if(!a[0].equals("")) {
				List<String> l = new ArrayList<>();
				for (String t: menu) if(t.startsWith(a[0].toLowerCase())) l.add(t);
				return l;
			} else return Arrays.asList(menu);
		} else if(a.length == 2) { // first argument done
			String[] y;
			if(a[0].equalsIgnoreCase("set") || a[0].equalsIgnoreCase("del") || a[0].equalsIgnoreCase("delete")) y = setdel;
			else if(a[0].equalsIgnoreCase("config")) y = confopt;
			else if(a[0].equalsIgnoreCase("home") || a[0].equalsIgnoreCase("delhome")) {
				ArrayList<String> list = API.listReplace(FileManager.listFiles(Main.getInstance().getDataFolder() + File.separator + "data"), ".yml", "");
				if(a[1].equals("")) return list;
				else {
					ArrayList<String> l = new ArrayList<>();
					for (String t : list) if (t.startsWith(a[1].toLowerCase())) l.add(t);
					return l;
				}
			}
			else return null;
			//for pre-made sets
			if (!a[1].equals("")) {
				List<String> l = new ArrayList<>();
				for (String t : y) if (t.startsWith(a[1].toLowerCase())) l.add(t);
				return l;
			} else return Arrays.asList(y);
		} else if(a.length == 3) {
			if(a[0].equalsIgnoreCase("home") || a[0].equalsIgnoreCase("delhome")) {
				return API.homelist(a[1]);
			}
		}
		return null;
	}
}
