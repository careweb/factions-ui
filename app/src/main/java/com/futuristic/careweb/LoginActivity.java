package com.futuristic.careweb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.futuristic.careweb.beans.AuthResponse;
import com.futuristic.careweb.services.AuthService;
import com.futuristic.careweb.session.Session;


public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";
    ProgressDialog progressDialog;
    private EditText loginInputUserId, loginInputPassword;
    private Button btnlogin;
    private Button btnLinkSignup;
    private AuthService service;
    private  Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = super.getApplicationContext();
        loginInputUserId = (EditText) findViewById(R.id.userId);
        loginInputPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.loginBtn);
        btnLinkSignup = (Button) findViewById(R.id.back);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(loginInputUserId.getText().toString(),
                        loginInputPassword.getText().toString());
            }
        });

        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
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

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}