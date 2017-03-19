package net.DeltaWings.Minecraft.BetterTP.Custom;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileManager {


	public List<String> listFiles(String path) {
		String[] t = new File(path).list();
		if(t == null) return null;
		else return Arrays.asList(t);
	}

	public List<String> listFiles(File path) {
		String[] t = path.list();
		if(t == null) return null;
		else return Arrays.asList(t);
	}

	public void delete(File path) {
		path.delete();
	}

	public void delete(String path) {
		File file = new File(path);
		file.delete();
	}

	public void createFile(String path) {
		try {
			new File(path).createNewFile();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void createFile(File path) {
		try {
			path.createNewFile();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void createFolder(File path) {
		path.mkdirs();
	}

	public void createFolder(String path) {
		new File(path).mkdirs();
	}
}