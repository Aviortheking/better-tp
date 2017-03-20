package net.DeltaWings.Minecraft.BetterTP;

import net.DeltaWings.Minecraft.BetterTP.Commands.*;
import net.DeltaWings.Minecraft.BetterTP.Custom.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Main extends JavaPlugin {

	public void log(String Message) {
		Bukkit.getLogger().log(Level.INFO, Message);
	}

	public void tp(String[] Type, Player Player) {
		if(Type[0].equals("spawn") || Type[0].equals("lobby")) {
			Config c = new Config("data/"+Type[0], "config");
			 Player.teleport(new Location(Bukkit.getServer().getWorld(c.getString("world")), c.getDouble("x"), c.getDouble("y"), c.getDouble("z")));
		} else {
			Config c = new Config("data", Player.getName());
			String t = Type[1] + ".";
			Player.teleport(new Location(Bukkit.getServer().getWorld(c.getString(t+"world")), c.getDouble(t+"x"), c.getDouble(t+"y"), c.getDouble(t+"z")));
		}
	}

	private static Main instance;
	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		getCommand("Spawn").setExecutor(new Spawn());
		getCommand("Lobby").setExecutor(new Lobby());
		getCommand("Home").setExecutor(new Home());
		getCommand("Bettertp").setExecutor(new Bettertp());
		getCommand("Sethome").setExecutor(new Sethome());
		getCommand("Delhome").setExecutor(new Delhome());
		getCommand("Homelist").setExecutor(new Homelist());

		config();

		log("Plugin Loaded !");
	}

	@Override
	public void onDisable() {
		log("Plugin Unloaded !");
	}

	private void config() {
		Config messages = new Config("", "messages");
		if(!messages.exist()) {
			messages.create();
			messages.set("global.permission", "&8 > &cYou do not have the permission to do thats !");
			messages.set("global.not-console", "This Command cannot be send throught console");
			messages.set("spawn.set", "&8 >&c The Spawn was set");
			messages.set("spawn.deleted", "&8 >&c The spawn was deleted");
			messages.set("spawn.not-set", "&8 >&c Spawn was not set !");
			messages.set("spawn.teleported", "&8 >&c You have been teleported to the spawn");
			messages.set("lobby.set", "&8 >&c The lobby was set");
			messages.set("lobby.deleted", "&8 >&c The lobby was deleted");
			messages.set("lobby.not-set", "&8 >&c lobby was not set !");
			messages.set("lobby.teleported", "&8 >&c You have been teleported to the lobby");
			messages.set("home.teleported", "&8 >&c You have been teleported to you home : [home]");
			messages.set("home.set", "&8 >&c Your home [home] has been set");
			messages.set("home.deleted", "&8 >&c Your home [home] has been deleted");
			messages.set("home.dont-exist", "&8 >&c Your home [home] do not exist");
			messages.set("home.teleported", "&8 >&c You have been teleported to your home  : [home]");
			messages.set("help.top", "&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c= &2BetterTP &c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-");
			messages.set("help.bottom", "&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c= &2BetterTP &c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-");
			messages.save();
		}


	}
}
