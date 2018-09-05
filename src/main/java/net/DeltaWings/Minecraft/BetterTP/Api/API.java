package net.DeltaWings.Minecraft.BetterTP.Api;

import java.util.ArrayList;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;

/**
 * API
 */
public class API {

	public static ArrayList<String> homelist(String player) {
		Config c = new Config("data", player);
		if(c.exist()) {
			return c.getSection("");
		}
		return new ArrayList<>();
	}
}