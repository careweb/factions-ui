package com.futuristic.careweb.services;

import com.futuristic.careweb.beans.AuthRequest;
import com.futuristic.careweb.beans.AuthResponse;
import com.futuristic.careweb.beans.User;
import com.futuristic.careweb.clients.AuthClient;
import com.google.gson.Gson;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Response;

import java.util.Map;

public class AuthService {
    private static final AsyncHttpClient client = AuthClient.getClient();
    public AuthResponse login(String userName, String password){
        AuthResponse authResp = null;
        try {
            AuthRequest request = new AuthRequest(userName, password);
            Gson gson = new Gson();
            String data = gson.toJson(request);
            String loginEndPoint = "auth/signin";
            BoundRequestBuilder authRequest = AuthClient.buildPostRequest(loginEndPoint, request, "login", "");
            Response response = new AuthClient().executeRequest(authRequest);
            authResp = gson.fromJson(response.getResponseBody(), AuthResponse.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return authResp;
    }

    public boolean logout(Map<String, String> map){
        Boolean resp = null;
        try {
            Gson gson = new Gson();
            String loginEndPoint = "auth/signout";
            BoundRequestBuilder authRequest = AuthClient.buildPostRequest(loginEndPoint, map, "logout", map.get("token"));
            Response response = new AuthClient().executeRequest(authRequest);
            resp = gson.fromJson(response.getResponseBody(), Boolean.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean signup(User user){
        Boolean resp = null;
        try {
            Gson gson = new Gson();
            String loginEndPoint = "auth/signup";
            BoundRequestBuilder authRequest = AuthClient.buildPostRequest(loginEndPoint, user, "signup", "");
            Response response = new AuthClient().executeRequest(authRequest);
            resp = gson.fromJson(response.getResponseBody(), Boolean.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }


}
