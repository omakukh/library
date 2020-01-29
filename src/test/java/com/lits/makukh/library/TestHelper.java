package com.lits.makukh.library;

import com.lits.makukh.library.requests.LoginBody;
import com.lits.makukh.library.responses.LoginResponse;
import com.lits.makukh.lits.LitsHttpClient;
import okhttp3.Headers;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public final static String baseUrl = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api";
    public static LitsHttpClient connect = new LitsHttpClient();
    private static String token;
    public final static String userId = "z73eb88MWuS3feLKDWXV";
    private static String userLoginUrl = baseUrl + "/auth/login";
    public static LoginBody credentials = new LoginBody("test.lnk.mail@gmail.com", "test123");

    public static String userLogin() {
        if (token == null) {
            Response response = connect.POST(userLoginUrl, Headers.of(), credentials);
            LoginResponse r = LitsHttpClient.convert(response, LoginResponse.class);
            token = r.getR().getAccess_token();
        }
        return token;
    }

    public static Headers authorizationHeader() {

        String token = TestHelper.userLogin();
        Map<String, String> authHeader = new HashMap<>();
        authHeader.put("Authorization", "Bearer " + token);
        authHeader.put("Content-Type", "application/json");
        return Headers.of(authHeader);
    }
}
