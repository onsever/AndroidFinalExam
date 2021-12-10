package com.onurcansever.lambtonfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText, passwordText;
    private Button loginButton;

    private final ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);

        addUser();

        loginButton.setOnClickListener(view -> {
            if (usernameText.getText().toString().equalsIgnoreCase(users.get(0).getUsername()) && passwordText.getText().toString().equalsIgnoreCase(users.get(0).getPassword())) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please enter your validated username and password!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUser() {
        users.add(new User(1, "user1", "password1"));
    }


}