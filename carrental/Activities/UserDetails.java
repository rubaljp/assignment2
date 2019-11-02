package com.example.carrental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carrental.GetterSetter.User;
import com.example.carrental.Others.Utils;
import com.example.carrental.R;
import java.util.ArrayList;
import java.util.UUID;

public class UserDetails extends AppCompatActivity implements View.OnClickListener
{
    public static ArrayList<User> users = new ArrayList<>();

    EditText edtFirstname, edtLastname, edtUsername, edtPassword, edtRole;
    Button btnSaveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);

        edtFirstname = (EditText) findViewById(R.id.edtFirstname);
        edtLastname = (EditText) findViewById(R.id.edtLastname);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRole = (EditText) findViewById(R.id.edtRole);

        btnSaveDetails = (Button) findViewById(R.id.btnSaveDetails);
        btnSaveDetails.setVisibility(View.VISIBLE);

        btnSaveDetails.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, EmployeeOperation.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnSaveDetails)
        {
            if(!edtFirstname.getText().toString().equals("") && !edtLastname.getText().toString().equals("") &&
                    !edtUsername.getText().toString().equals("") && !edtPassword.getText().toString().equals("") && !edtRole.getText().toString().equals(""))
            {
                User customer = new User();
                customer.setFirstname(edtFirstname.getText().toString().trim());
                customer.setLastname(edtLastname.getText().toString().trim());
                customer.setUsername(edtUsername.getText().toString().trim());
                customer.setPassword(edtPassword.getText().toString().trim());
                customer.setRole(edtRole.getText().toString().trim());

                if(users.size() > 0)
                {
                    for(User existingUser : users)
                    {
                        if(edtUsername.getText().toString().trim().equals(existingUser.getUsername()))
                            {
                            Toast.makeText(this, "Username is already exist", Toast.LENGTH_SHORT).show();
                        }
                        else if(!edtUsername.getText().toString().trim().equals(existingUser.getUsername()))
                        {
                            String uniqueID = UUID.randomUUID().toString();
                            customer.setUniqueId(uniqueID);
                            Utils.hasData = "1";
                            users.add(customer);

                            Toast.makeText(this, "Customer created successfully", Toast.LENGTH_SHORT).show();

                            edtFirstname.setText("");
                            edtLastname.setText("");
                            edtUsername.setText("");
                            edtPassword.setText("");
                            edtRole.setText("");
                        }
                    }
                }
                else
                {
                    String uniqueID = UUID.randomUUID().toString();
                    customer.setUniqueId(uniqueID);
                    Utils.hasData = "1";
                    users.add(customer);

                    Toast.makeText(this, "Customer created successfully", Toast.LENGTH_SHORT).show();

                    edtFirstname.setText("");
                    edtLastname.setText("");
                    edtUsername.setText("");
                    edtPassword.setText("");
                    edtRole.setText("");
                }
            }
            else
            {
                Toast.makeText(this, "Please fill the entire details !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}