package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    TextView username = findViewById(R.id.UserID);
    TextView password = findViewById(R.id.Password);

    Button login = findViewById(R.id.LogIn);
    Button forgot = findViewById(R.id.forgot);
    Button signup = findViewById(R.id.SignUp);
    Button employee = findViewById(R.id.employee);


}
