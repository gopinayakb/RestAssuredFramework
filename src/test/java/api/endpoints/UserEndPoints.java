package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//this class is created to perform Create, Get, Update, Delete requests for the user API.
public class UserEndPoints {

	public static Response createUser(User userPayLoad) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(userPayLoad)
				.when().post(Routes.post_url);

		return response;
	}

	public static Response getUser(String userName) {

		Response response = given().pathParam("username", userName)
				.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String userName, User userPayLoad) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(userPayLoad)
				.when().put(Routes.update_url);

		return response;
	}
	
	public static Response deleteUser(String userName) {

		Response response = given().pathParam("username", userName)
				.when().delete(Routes.delete_url);

		return response;
	}
}
