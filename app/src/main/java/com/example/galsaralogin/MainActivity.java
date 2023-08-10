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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText edtUsrName, edtPassword;

    String userName, pass1;

    int userID;

    String email,galsara,testuser;


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

        //edtUsrName.setText(getIntent().getStringExtra("USERNAME"));

        try {
            FileInputStream fileInputStream = openFileInput("login.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line11;
            String[] credd = new String[0];

            while ((line11 = bufferedReader.readLine()) != null){
                 credd = line11.split(","); //credd[0] --> email, credd[1] --> username, credd[2] --> password
            }
            bufferedReader.close();
           // Toast.makeText(this, credd[0] + credd[1] + credd[2], Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtUsrName.getText().toString();
                pass1 = edtPassword.getText().toString();


                try {
                    FileInputStream fileInputStreamInternal = openFileInput("login.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStreamInternal);


                   // InputStream inputStream = getAssets().open("login.txt");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line;

                    while ((line = bufferedReader.readLine()) != null){
                        String[] creds = line.split(",");


                        if (creds.length == 3){
                            String usrName = creds[1];
                            String pass = creds[2];


                            if (userName != null && pass1 != null){

                                if (usrName.equals(userName) && pass.equals(pass1)){


                                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                                    InputStream inputStream1 = null;
                                    try {
                                        FileInputStream fileInputStreamAccount = openFileInput("account_info.txt");
                                        InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStreamAccount);
                                       // inputStream1 = getAssets().open("account_info.txt");
                                        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);

                                        String line1;

                                        while ((line1 = bufferedReader1.readLine()) != null) {
                                            String[] creds1 = line1.split(",");

                                             email = creds1[0];
                                             galsara = creds1[1];
                                             testuser = creds1[2];

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
                                    intent.putExtra("USER", email);
                                    intent.putExtra("USERNAME", galsara);
                                    intent.putExtra("PASSWORD", testuser);
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