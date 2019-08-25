package com.futuristic.careweb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.futuristic.careweb.beans.User;

public class SignUpActivity extends Activity {
    ProgressDialog progressDialog;
    private EditText firstName, lastName,userId,password,email,phone,address;
    private Button btnLinkSignup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        User user = new User();
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        userId = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);

        btnLinkSignup = (Button) findViewById(R.id.submitBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * RestFul call to signup endpoint
                 */

            }
        });


    }
}
