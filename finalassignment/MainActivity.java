package com.example.finalassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id = findViewById(R.id.id);
        EditText password = findViewById(R.id.password);

        Button login = findViewById(R.id.button);

        TextView employee = findViewById(R.id.employee);
        TextView forgot = findViewById(R.id.forgot);
        TextView signup = findViewById(R.id.signup);

        final Intent intent_signup = new Intent(MainActivity.this,Signup.class);
        final Intent intent_car = new Intent(MainActivity.this,Cars.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_car);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_signup);
            }
        });

    }
}
