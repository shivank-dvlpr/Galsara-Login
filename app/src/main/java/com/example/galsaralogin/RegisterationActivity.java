package com.example.galsaralogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail,edtUsername, edtPass;
    Button btnSubmit, btnbtn;

    String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        getSupportActionBar().hide();

        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

       // btnbtn = findViewById(R.id.btnbtn);

        btnSubmit.setOnClickListener(this);
       // btnbtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit){

            String email = edtEmail.getText().toString();
            String userName = edtUsername.getText().toString();
            String pass = edtPass.getText().toString();

            if(email.isEmpty() || userName.isEmpty() || pass.isEmpty()){

                Toast.makeText(this, "Fields Cannot Be Empty !", Toast.LENGTH_SHORT).show();
                edtEmail.setText("");
                edtUsername.setText("");
                edtPass.setText("");

            }else {
                userData = email + "," + userName + "," + pass + "\n";
                try {
                    register();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(RegisterationActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", userName);
                startActivity(intent);
                Toast.makeText(this, "Account Created!\nPlease Login to Continue", Toast.LENGTH_SHORT).show();
            }

        }/*else if (view.getId() == R.id.btnbtn){
            Intent intent = new Intent(RegisterationActivity.this, MainActivity.class);
            startActivity(intent);
        }*/
    }

    private void register() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileOutputStream fileOutputStreamLogin = openFileOutput("login.txt", MODE_PRIVATE);
            FileOutputStream fileOutputStreamAccount = openFileOutput("account_info.txt", MODE_PRIVATE);

            fileOutputStreamAccount.write(userData.getBytes());
            fileOutputStreamLogin.write(userData.getBytes());

            fileOutputStreamAccount.close();
            fileOutputStreamLogin.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}