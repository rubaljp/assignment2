package com.example.finalassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText first_name = findViewById(R.id.f_name);
        EditText last_name = findViewById(R.id.L_name);
        EditText username = findViewById(R.id.Username);
        EditText role = findViewById(R.id.Role);

        Button create_account = findViewById(R.id.create);

        final Intent intent_created = new Intent(Signup.this,MainActivity.class);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_created);
            }
        });

    }
}
