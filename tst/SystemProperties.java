
public class SystemProperties {

	public static String[] systemProperties = {
		"file.separator",
		"java.class.path",
		"java.home",
		"java.vendor",
		"java.vendor.url",
		"java.version",
		"line.separator",
		"os.arch",
		"os.name",
		"os.version",
		"path.separator",
		"user.dir",
		"user.home",
		"user.name"
	};
	
	public static void main(String[] args) {
		for (int i = 0; i < systemProperties.length; i++) {
			System.out.println(systemProperties[i]+" = "+System.getProperty(systemProperties[i]));
		}
	}

}
