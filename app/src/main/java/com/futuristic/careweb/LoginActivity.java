package com.futuristic.careweb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.futuristic.careweb.beans.AuthResponse;
import com.futuristic.careweb.services.AuthService;
import com.futuristic.careweb.session.Session;
import com.futuristic.careweb.utilities.Utility;


public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";
    private EditText loginInputUserId, loginInputPassword;
    private Button btnlogin;
    private Button btnLinkBack;
    private AuthService service;
    private Utility utility;
    private  Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = super.getApplicationContext();
        utility = Utility.getInstance(context);
        loginInputUserId = (EditText) findViewById(R.id.userName);
        loginInputPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.loginBtn);
        btnLinkBack = (Button) findViewById(R.id.back);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(loginInputUserId.getText().toString(),
                        loginInputPassword.getText().toString());
            }
        });

        btnLinkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser( final String userName, final String password){
        service = new AuthService();
        AuthResponse response = service.login(userName, password);
        if(response.getToken() != null){
            Session session = new Session(context);
            session.setInfo("user", response.getUsername());
            session.setInfo("token", response.getToken());
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

            startActivity(intent);
        }
        System.out.println(response);
    }
}