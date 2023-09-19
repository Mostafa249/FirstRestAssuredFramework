package api.Tests;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.Logs;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {
    Faker faker;
    User userPayload;

    public Logs log;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void testCreateUser() {
        log.info("****** Creating User ******");
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("****** User Created Successfully  ******");
    }

    @Test(priority = 2)
    public void testReadUser() {
        log.info("****** Reading User ******");
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("****** user info is displayed ******");
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        log.info("****** Updating User ******");
        //Update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        //Checking data after update
        Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        responseAfterUpdate.then().log().body();
        log.info("****** User is updated successfully ******");
    }



    @Test(priority = 4)
    public void testDeleteUser() {
        log.info("****** Deleting User ******");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("****** User is deleted successfully ******");
    }
}
