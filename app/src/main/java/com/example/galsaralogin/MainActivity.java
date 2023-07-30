package com.example.galsaralogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText edtUsrName, edtPassword;

    String userName, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnSubmit = findViewById(R.id.btnSubmit);
        edtUsrName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtUsrName.getText().toString();
                pass1 = edtPassword.getText().toString();


                try {
                    InputStream inputStream = getAssets().open("login.txt");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;

                    while ((line = bufferedReader.readLine()) != null){
                        String[] creds = line.split(",");


                        if (creds.length == 2){
                            String usrName = creds[0];
                            String pass = creds[1];


                            if (userName != null && pass1 != null){

                                if (usrName.equals(userName) && pass.equals(pass1)){

                                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    return;
                                }
                            }

                        }


                    }
                    Toast.makeText(MainActivity.this, "Please Check Your Username or Password !", Toast.LENGTH_LONG).show();
                    edtUsrName.setText("");
                    edtPassword.setText("");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }





            }
        });

    }
}