package net.DeltaWings.Minecraft.BetterTP;

import java.io.File;
import java.util.concurrent.Callable;

import org.bstats.bukkit.Metrics;

import net.DeltaWings.Minecraft.BetterTP.Libs.Config;
import net.DeltaWings.Minecraft.BetterTP.Libs.FileManager;

public class Charts {

	private Main i = Main.getInstance();
	private String j = File.separator;
	private final File d = new File(i.getDataFolder() + j + "data");

	public Charts(Metrics metrics) {

		metrics.addCustomChart(new Metrics.SingleLineChart("home_number", new Callable<Integer>(){
		
			@Override
			public Integer call() throws Exception {
				Integer result = 0;
				for (String conf : FileManager.listFiles(d)) {
					Config c = new Config("data", conf);
					result += c.getSection("").size();
				}
				return result;
			}
		}));
	}
}
