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
import java.util.Objects;

public class ModifyUserDetails extends AppCompatActivity implements View.OnClickListener

{
    private ArrayList<User> users = UserDetails.users;

    EditText edtFirstname, edtLastname, edtUsername, edtPassword, edtRole;
    Button btnUpdateDetails;

    User user;
    int position;

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

        position = Objects.requireNonNull(getIntent().getExtras()).getInt("position");
        user = users.get(position);

        edtFirstname.setText(user.getFirstname().trim());
        edtLastname.setText(user.getLastname().trim());
        edtUsername.setText(user.getUsername().trim());
        edtPassword.setText(user.getPassword().trim());
        edtRole.setText(user.getRole().trim());

        btnUpdateDetails = (Button) findViewById(R.id.btnUpdateDetails);
        btnUpdateDetails.setVisibility(View.VISIBLE);

        btnUpdateDetails.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, EmployeeOperation.class);
        startActivity(intent);
        finish();
    }

    private boolean alreadyExist()
    {
        for(User existingUser : users)
        {
            if(edtUsername.getText().toString().trim().equals(existingUser.getUsername()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnUpdateDetails)
        {
            if(!edtFirstname.getText().toString().equals("") && !edtLastname.getText().toString().equals("") &&
                    !edtUsername.getText().toString().equals("") && !edtPassword.getText().toString().equals("") && !edtRole.getText().toString().equals(""))
            {
                User updateUser = new User();
                updateUser.setFirstname(edtFirstname.getText().toString().trim());
                updateUser.setLastname(edtLastname.getText().toString().trim());
                updateUser.setUsername(edtUsername.getText().toString().trim());
                updateUser.setPassword(edtPassword.getText().toString().trim());
                updateUser.setRole(edtRole.getText().toString().trim());

                if(users.size() > 0)
                {
                    for(User existingUser : users)
                    {
                        if(!edtUsername.getText().toString().trim().equals(user.getUsername()))
                        {
                            if(alreadyExist())
                                Toast.makeText(this, "Username is already exist", Toast.LENGTH_SHORT).show();
                        }
                        else if(!edtUsername.getText().toString().trim().equals(existingUser.getUsername()) ||
                                        edtUsername.getText().toString().trim().equals(user.getUsername()))
                        {
                            users.set(position, updateUser);
                            Toast.makeText(this, "Customer updated successfully", Toast.LENGTH_SHORT).show();

                            edtFirstname.setText("");
                            edtLastname.setText("");
                            edtUsername.setText("");
                            edtPassword.setText("");
                            edtRole.setText("");
                        }
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Entire details needed to update details !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}