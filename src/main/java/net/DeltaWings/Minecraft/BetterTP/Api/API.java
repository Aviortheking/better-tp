package net.DeltaWings.Minecraft.BetterTP.Api;

import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import net.DeltaWings.Minecraft.BetterTP.Main;
import net.DeltaWings.Minecraft.BetterTP.Libs.Config;
import net.DeltaWings.Minecraft.BetterTP.Libs.FileManager;

/**
 * API
 */
public class API {

	private static final String datafolder = "data";
	private static final String playerfolder = "players";

	public static String getDataFolder() {
		return datafolder;
	}

	public static String getPlayersFolder() {
		return getDataFolder() + File.separator + playerfolder;
	}

	public static ArrayList<String> homelist(String player) {
		Config c = new Config(getPlayersFolder(), player);
		if(c.exist()) {
			return c.getSection("");
		}
		return new ArrayList<>();
	}

	public static ArrayList<String> listPlayersWithHome() {
		return listReplace(FileManager.listFiles(Main.getInstance().getDataFolder() + File.separator + getPlayersFolder()), ".yml", "");
	}

	//functions without plugin link

	public static ArrayList<String> listReplace(ArrayList<String> list, String replaced, String replacement) {
		ListIterator<String> it = list.listIterator();
		while(it.hasNext()) {
			it.set(it.next().replace(replaced, replacement));
		}
		return list;
	}
}