package objectRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestObjectRepository {
	public Properties config;
	public FileInputStream propertiesFile;
	
	public TestObjectRepository(String filePath) throws IOException {
		
		config = new Properties();
		propertiesFile = new FileInputStream("src/test/resources/Properties/"+filePath+"");
		config.load(propertiesFile);
		
		
}

}