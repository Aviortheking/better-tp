package net.DeltaWings.Minecraft.BetterTP.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Delta Wings on 19/03/2017 at.01:40
 */
public class Bettertp implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if(a.length == 2) {
			if(a[0].equalsIgnoreCase("set")) {
				if(a[1].equalsIgnoreCase("spawn")) {

				} else if(a[1].equalsIgnoreCase("lobby")) {

				}
			} else if(a[0].equalsIgnoreCase("del") || a[0].equalsIgnoreCase("delete")) {
				if(a[1].equalsIgnoreCase("spawn")) {

				} else if(a[1].equalsIgnoreCase("lobby")) {

				}
			}
		} else if(a.length == 1) {
			if(a[0].equalsIgnoreCase("help")) {
				
			}
		}
		return false;
	}
}
