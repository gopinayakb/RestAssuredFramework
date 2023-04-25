package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

//this class is used for test driven data
public class DDTests {

	// below method is for Creating multiple users using DataProvider annotation.
	// Note: when DataProvider is in the same class then we no need to give the
	// dataProviderClass name.
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id, String uName, String fName, String lName, String email, String pwd, String ph) {

		// userPayLoad is instance of User class i.e POJO class.
		User userPayLoad = new User();

		// setting data for pojo class using DataProvider annotation.
		userPayLoad.setId(Integer.parseInt(id));
		userPayLoad.setUsername(uName);
		userPayLoad.setFirstName(fName);
		userPayLoad.setLastName(lName);
		userPayLoad.setEmail(email);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(ph);

		// calling createUser method from UserEndPoints class and passing argument
		// userPayLoad and capturing the response.
		Response response = UserEndPoints.createUser(userPayLoad);
		
		//validating status code
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2, dataProvider="userName", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName) {
		// calling deleteUser method from UserEndPoints class and passing argument userName then it will deletes based on user name.
		Response response = UserEndPoints.deleteUser(userName);
		
		//validating status code.
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
