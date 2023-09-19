package api.Tests;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DDUserTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String userName, String fName, String lName, String email, String password, String phone) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setPassword(password);
        userPayload.setEmail(email);
        userPayload.setPhone(phone);
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void TestReadUser(String userName){
        Response response = UserEndpoints.readUser(userName);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
