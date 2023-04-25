package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

//all the test cases are written in this class
public class UserTests2 {

	Faker faker;
	User userPayLoad;

	@BeforeClass
	public void setupData() {
		// Faker class provides the random data
		faker = new Faker();
		// userPayLoad is the instance of the User class i.e POJO class & it is passed as request body
		userPayLoad = new User();
		// setting values for pojo class using setter methods.
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5, 10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void testPostUser() {
		// calling createUser method from UserEndPoints class and passing argument
		// userPayLoad and capturing the response.
		Response response = UserEndPoints2.createUser(userPayLoad);

		// logging the response.
		response.then().log().all();

		// validating the status code for creating(post) user.
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {
		// calling getUser method from UserEndPoints class and passing argument username
		// and capturing the response.
		Response response = UserEndPoints2.getUser(this.userPayLoad.getUsername());

		// logging the response.
		response.then().log().all();

		// validating the status code for creating(post) user.
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		// updating data using payload
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200); // validating status code.

		//checking data after update.
		Response responseAfterUpdate = UserEndPoints.getUser(this.userPayLoad.getUsername());	
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

	
	@Test(priority=4)
	public void testDeleteUserByName() {
		Response response = UserEndPoints2.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200); // validating status code.
	}
}
