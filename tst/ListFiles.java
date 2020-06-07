import java.io.File;

import common.Config;

public class ListFiles {
	
	public static final Config config = new Config("config");
	
	public static void main(String[] args) {
		File cacheFolder = new File(
				System.getProperty("user.home")+File.separator
				+config.getString("discordCache_"+System.getProperty("os.name").replace(' ', '_'))
		);
		File[] files = cacheFolder.listFiles();
		for (File file: files) {
			System.out.println(file.getName());
		}
	}
}
