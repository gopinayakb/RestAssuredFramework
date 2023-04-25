package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import api.utilities.ReadProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//here url's are sent from properties file.
//this is class created to perform Create, Get, Update, Delete requests the user API.
public class UserEndPoints2 {

/*	static ReadProperties read = new ReadProperties(); //this class is imported from api.utilities package.
	public static String post_url = read.getPostUrl();
	public static String get_url = read.getGetUrl();
	public static String update_url = read.getUpdateUrl();
	public static String delete_url = read.getdeleteUrl();
*/	
	
	//or below is another way to get data from the properties file.
	
	//method is created for getting URL's from the properties file. 
	public static ResourceBundle getURL() {
		//ResourceBundle  contains the local files
		//getBundle() by default knows the location of routes file and loads the properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	//Note: getURL method is static, so we can call directly in static methods.
	
	public static Response createUser(User userPayLoad) {
		//getURL() method returns the object of the ResourceBundle on that we are getting data from properties file.
		String post_url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userPayLoad)
				.when().post(post_url);

		return response;
	}

	public static Response getUser(String userName) {

		String get_url = getURL().getString("get_url");
		
		Response response = given().pathParam("username", userName)
				.when().get(get_url);

		return response;
	}

	public static Response updateUser(String userName, User userPayLoad) {

		String update_url = getURL().getString("update_url");
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(userPayLoad)
				.when().put(update_url);

		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getURL().getString("delete_url");

		Response response = given().pathParam("username", userName)
				.when().delete(delete_url);

		return response;
	}
}
