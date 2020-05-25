package net.DeltaWings.Minecraft.BetterTP;

import net.DeltaWings.Minecraft.BetterTP.Api.API;
import net.DeltaWings.Minecraft.BetterTP.Commands.*;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

import net.DeltaWings.Minecraft.BetterTP.TabCompleter.*;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.concurrent.Callable;


public final class Main extends JavaPlugin {

	private PluginDescriptionFile desc;

	private static Main instance;
	public static Main getInstance() {
		return instance;
	}

	public static void log(String message) {
		getInstance().getLogger().log(Level.INFO, message);
	}

	public static void debug(String message) {
		if(new Config("", "config").getBoolean("debug", true)) getInstance().getLogger().log(Level.INFO, "[Debug] > " + message);
	}

	public static void error(String message) {
		getInstance().getLogger().log(Level.WARNING, message);
	}

	@Override
	public void onEnable() {
		instance = this;
		debug("Loading Variables");
		desc = this.getDescription();
		//PluginManager pm = getServer().getPluginManager();
		PluginCommand bettertp = getCommand("Bettertp");
		PluginCommand home = getCommand("Home");
		PluginCommand delhome = getCommand("Delhome");
		debug("Loaded variables");

		debug("Loading Configuration");
		try {
			config();
		} catch ( IOException e ) {
			e.printStackTrace();
			error("Error: Config not generated");
			error("Shutting Down for security...");
			this.getPluginLoader().disablePlugin(this);
			return
		}
		debug("Loaded Configuration !");

		//debug("Loading Events");
		//pm.registerEvents(new Event(), this);
		//debug("Loaded Events");

		debug("Loading Commands");

		//getCommand("Command").setExecutor(new Command());
		bettertp.setExecutor(new Bettertp());
		bettertp.setTabCompleter(new BettertpTab());
		getCommand("Lobby").setExecutor(new LobbySpawn());
		home.setExecutor(new Home());
		home.setTabCompleter(new HomeTab());
		getCommand("Sethome").setExecutor(new Sethome());
		delhome.setExecutor(new Delhome());
		delhome.setTabCompleter(new DelhomeTab());
		getCommand("Homelist").setExecutor(new Homelist());

		debug("Loaded Commands");
		log("Loaded !");
	}

	@Override
	public void onDisable() {
		log("Unloaded !");
	}

	private void config() throws IOException {
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
			messages.set("home.max", "&8 >&c You already have [max] homes");
			messages.set("help.top", "&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c= &2[help] &c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-");
			messages.set("help.bottom", "&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c= &2[help] &c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-&c=&4-");
			messages.save();
		}

		Config c = new Config("", "config");
		if(!c.exist()) {
			c.create();
			c.header("How to config : https://bitbucket.org/delta-wings/bettertp/wiki/");
			c.set("debug", false);
			c.set("metrics", true);
			c.set("donttouch.version", 1);
			c.set("maxhomes.default", 1);
			c.set("spawn.work", "world");
			c.set("spawn.server.lobby", false);
			//new String[]{"debug","maxhomes.default","spawn.work","spawn.server.lobby"};
			c.save();
		}
	}
}
