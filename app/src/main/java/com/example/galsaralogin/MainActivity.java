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

    int userID;

    String galsara,testuser;


    Account_Model account_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnSubmit = findViewById(R.id.btnSubmit);
        edtUsrName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        account_model = new Account_Model();

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

                                    InputStream inputStream1 = null;
                                    try {
                                        inputStream1 = getAssets().open("account_info.txt");
                                        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));

                                        String line1;

                                        while ((line1 = bufferedReader1.readLine()) != null) {
                                            String[] creds1 = line1.split("-");


                                             galsara = creds1[0];
                                             testuser = creds1[1];

                                            if (userName.equals("galsara")){
                                                userID = 0;
                                                account_model.setUser(galsara);
                                            }else {
                                                userID = 1;
                                                account_model.setUser(testuser);
                                            }

                                        }


                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }


                                    //intent.putExtra("USERNAME", account_model.getUser());
                                    intent.putExtra("USER", userID);
                                    intent.putExtra("GALSARA", galsara);
                                    intent.putExtra("TESTUSER", testuser);
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