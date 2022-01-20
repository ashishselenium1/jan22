package temp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadingProp {

	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fs = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fs);
		
		System.out.println(prop.getProperty("url"));
	}

}
