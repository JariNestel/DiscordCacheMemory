package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private Properties properties;
	
	public Config(String filename) {
		properties = new Properties();
		
		//defaults
		try {
			InputStream inputStream = Config.class.getResourceAsStream(filename+".properties");		
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {}

		//primary (production)
		FileInputStream inputStreamLocal;
		try {
			inputStreamLocal = new FileInputStream(filename+".properties");
			try {
				properties.load(inputStreamLocal);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			
			System.out.println("No config file found, writing out defaults");
			try (FileOutputStream outputStream = new FileOutputStream(filename+".properties")) {

	            int read;
	            byte[] bytes = new byte[1024];
	            
	            InputStream inputStream = Config.class.getResourceAsStream(filename+".properties");	
	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }

				// commons-io
	            //IOUtils.copy(inputStream, outputStream);

	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//secondary (development)
		try {
			inputStreamLocal = new FileInputStream(filename+".mine.properties");
			properties.load(inputStreamLocal);
		} catch (IOException ignore) {
		}
	}
	
	public String getString(String key) {
		return properties.getProperty(key);
	}
	
	public byte	   getByte(String key)    {return Byte.parseByte(getString(key));}
	public short   getShort(String key)   {return Short.parseShort(getString(key));}
	public int     getInt(String key)     {return Integer.parseInt(getString(key));}
	public long    getLong(String key)    {return Long.parseLong(getString(key));}
	public float   getFloat(String key)   {return Float.parseFloat(getString(key));}
	public double  getDouble(String key)  {return Double.parseDouble(getString(key));}
	public boolean getBoolean(String key) {return Boolean.parseBoolean(getString(key));}
	public char    getChar(String key)    {return getString(key).charAt(0);}
}
