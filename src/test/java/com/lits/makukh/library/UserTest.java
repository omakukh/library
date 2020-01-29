package com.lits.makukh.library;

import com.lits.makukh.library.responses.UserInfo;
import com.lits.makukh.lits.LitsHttpClient;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {


    @Test(groups = {"All", "User"},
            description = "Verify user login")
    public void testLogin() {
        String token = TestHelper.userLogin();
        Assert.assertNotNull(token);
        Assert.assertNotEquals(token, "");
    }


    @Test(groups = {"All", "User"},
            description = "Verify get user info")
    public void testGetUserInfo(){

        String userInfoUrl = TestHelper.baseUrl + "/v1/users/" + TestHelper.userId;
        Response response = TestHelper.connect.GET(userInfoUrl,TestHelper.authorizationHeader());
        UserInfo userInfo = LitsHttpClient.convert(response,UserInfo.class);
        Assert.assertEquals(userInfo.getEmail(), TestHelper.credentials.getEmail());
    }


}
