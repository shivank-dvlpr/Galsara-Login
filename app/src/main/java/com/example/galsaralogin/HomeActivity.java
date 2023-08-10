package com.example.galsaralogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {

    TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Account_Model account_model = new Account_Model();
        txtTest = findViewById(R.id.txtTest);

        txtTest.setText("Welcome "+getIntent().getStringExtra("USERNAME") + "\nYour Email is " + getIntent().getStringExtra("USER"));

     /*   if (getIntent().getStringExtra("USER").equals("galsara@email.com")){
            account_model.setUser(getIntent().getStringExtra("GALSARA"));
            txtTest.setText(account_model.getUser());
        }else {
            account_model.setUser(getIntent().getStringExtra("TESTUSER"));
            txtTest.setText(account_model.getUser());
        }
*/


    }
}