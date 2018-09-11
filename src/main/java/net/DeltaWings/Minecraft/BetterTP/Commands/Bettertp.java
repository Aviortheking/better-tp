package net.DeltaWings.Minecraft.BetterTP.Commands;

import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Bettertp implements CommandExecutor {

	Config m = new Config("", "messages");
	Config c = new Config("", "config");

	@Override
	public boolean onCommand(CommandSender s, Command unu2, String unu1, String[] a) {
		if((s instanceof Player && s.hasPermission("bettertp.admin")) || s instanceof ConsoleCommandSender) {
			if(a.length == 1) {
				if(a[0].equalsIgnoreCase("help")) help(s);
				return true;
			} else if(a.length >= 2 && a.length <= 3) {
				if(a.length == 2 && (a[0].equalsIgnoreCase("set") || a[0].equalsIgnoreCase("del"))) {
					if(a[1].equalsIgnoreCase("spawn") || a[1].equalsIgnoreCase("lobby")) set(s, a[1]);
					else if(a[1].equalsIgnoreCase("spawn") || a[1].equalsIgnoreCase("lobby")) del(s, a[1]);
					return true;
				} else if(a[0].equalsIgnoreCase("home") || a[0].equalsIgnoreCase("delhome") || a[0].equalsIgnoreCase("homelist")) {
					String home = a.length == 3 ? a[2] : "home"; //check if player has specified a home
					if(a[0].equalsIgnoreCase("home")) home(s, a[1], home);
					if(a[0].equalsIgnoreCase("homelist")) homelist(s, a[1]);
					if(a[0].equalsIgnoreCase("delhome")) delhome(s, a[1], home);
					return true;
				} else return false;
			} else return false;
		} else {
			//sendmessage you don't have the permission
			return true;
		}
	}

	private void set(CommandSender s, String whattoset) {
		if(s instanceof ConsoleCommandSender) {
			s.sendMessage("The console can't use this command");
		} else {
			Config t = new Config(API.getDataFolder(), whattoset);
			if(t.exist()) {
				//sendmessage spawn/lobby already set
			} else {
				try {
					t.create();
					Location l = ((Player) s).getLocation();
					t.set("world", l.getWorld().getName());
					t.set("x", l.getX());
					t.set("y", l.getY());
					t.set("z", l.getZ());
					t.save();
					//sendmessage spawn/lobby successfully set !
				} catch (IOException e) {
					e.printStackTrace();
					//sendmessage error, writing the configuration file please report
				}
			}
		}
	}

	private void del(CommandSender s, String whattoset) {
		Config t = new Config(API.getDataFolder(), whattoset);
		if(t.exist()) {
			try {
				t.delete();
				//sendmssage successfully removed the spawn/lobby
			} catch (Exception e) {
				e.printStackTrace();
				//sendmessage error, config couldn't be deleted
			}
		} else {
			//sendmessage there is no spawn/lobby
		}
	}
	
	private void help(CommandSender s) {
		s.sendMessage(m.getString("help.top").replace("[help]", "BetterTP").replace("&", "§"));
		s.sendMessage("&4| &9/btp &lset &llobby/spawn".replace("&", "§"));
		s.sendMessage("&4| &9To set spawn or lobby".replace("&", "§"));
		s.sendMessage("&4| &9/btp &ldel &llobby/spawn".replace("&", "§"));
		s.sendMessage("&4| &9To delete spawn/lobby".replace("&", "§"));
		s.sendMessage(m.getString("help.bottom").replace("[help]", "BetterTP").replace("&", "§"));
	}
	
	private void home(CommandSender s, String player, String home) {
		Config cp = new Config(API.getPlayersFolder(), player);
		if(cp.exist() && cp.isSet(home)) {
			String t = home+".";
			((Player) s).teleport(new Location(Bukkit.getServer().getWorld(cp.getString(t+"world")), cp.getDouble(t+"x"), cp.getDouble(t+"y"), cp.getDouble(t+"z")));
			((Player) s).sendMessage("Sended you to " + player + " home : " + home);
		} else ; //sendmessage player has no home "homename"
		
	}

	private void delhome(CommandSender s, String player, String home) {
		Config cp = new Config(API.getPlayersFolder(), player);
		if(cp.exist() && cp.isSet(home)) {
			cp.set(home, null);
			try {
				cp.save();
			} catch ( IOException e ) {
				e.printStackTrace();
				s.sendMessage("Error, Please call an Admin !");
			}
			s.sendMessage(m.getString("home.deleted").replace("[home]", home).replace("&", "§"));
		} else ; //send message player has no home "homename"
	}

	private void homelist(CommandSender s, String player) {
		Config c = new Config("", "messages");
		s.sendMessage(c.getString("help.top").replace("[help]", "Homelist").replace("&", "§"));
		for(String a : API.homelist(s.getName())) {
			s.sendMessage(("&4| &9").replace("&", "§") + a);
		}
		s.sendMessage(c.getString("help.bottom").replace("[help]", "Homelist").replace("&", "§"));

	}
}


/*
	/btp
	set <lobby|spawn>
	del <lobby|spawn>
	help
	homelist <player>
	home <player> [homename]
	selhome <player [homename]
*/