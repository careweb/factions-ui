package com.futuristic.careweb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.futuristic.careweb.beans.User;
import com.futuristic.careweb.services.AuthService;
import com.futuristic.careweb.utilities.Utility;

public class SignUpActivity extends Activity {
    private static final String TAG = "SignUpActivity";
    private EditText firstName, lastName,userName,password,email,phone,address, country;
    private Button btnLinkSignup;
    private Button btnLinkBack;
    private Utility utility;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context = super.getApplicationContext();
        User user = new User();
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        country = (EditText) findViewById(R.id.country);


        btnLinkSignup = (Button) findViewById(R.id.submitBtn);
        btnLinkBack = (Button) findViewById(R.id.back);
        utility = Utility.getInstance(context);
        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setUserName(userName.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setCountry(country.getText().toString());
                signup(user);
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
    private void signup(User user){
        AuthService service = new AuthService();
        if(service.signup(user)){
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
