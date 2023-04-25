package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

//this class will read the data present in .properties files.
public class ReadProperties {

	//Properties class is used to read the data present in the properties file.
	Properties prop;

	public ReadProperties() {//constructor

		File resource = new File("./src/test/resources/routes.properties");//location of the properties file
		try {
			FileInputStream fis = new FileInputStream(resource);//creates connection to the actual file.
			prop = new Properties();//instance of Properties class
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}
	
	public String getPostUrl() {
		String postUrl = prop.getProperty("post_url");
		return postUrl;
	}
	
	public String getGetUrl() {
		String getUrl = prop.getProperty("get_url");
		return getUrl;
	}
	
	public String getUpdateUrl() {
		String updateUrl = prop.getProperty("update_url");
		return updateUrl;
	}
	
	public String getdeleteUrl() {
		String deleteUrl = prop.getProperty("delete_url");
		return deleteUrl;
	}
	
}
