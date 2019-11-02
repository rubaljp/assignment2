package com.example.carrental.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carrental.GetterSetter.User;
import com.example.carrental.Others.Utils;
import com.example.carrental.R;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    EditText edtUsername, edtPassword;
    Button btnLogin, btnEmployee;
    Spinner selectRole;

    ArrayList<User> userArrayList = UserDetails.users;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        selectRole = (Spinner) findViewById(R.id.selectRole);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnEmployee = (Button) findViewById(R.id.btnEmployee);

        ArrayList<String> role = new ArrayList<>();
        role.add("Please select role");
        role.add("Customer");
        role.add("Employee");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, role);
        selectRole.setAdapter(adapter);

        btnLogin.setOnClickListener(this);
        btnEmployee.setOnClickListener(this);
    }

    private boolean isUsernameExist()
    {
        if(userArrayList.size() == 0)
            Utils.hasData = "0";
        else
        {
            for(User user : userArrayList)
            {
                if(edtUsername.getText().toString().trim().equals(user.getUsername()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private int checkRole()
    {
        if(userArrayList.size() == 0)
            Utils.hasData = "0";
        else
        {
            for(User user : userArrayList)
            {
                if(edtUsername.getText().toString().trim().equals(user.getUsername()))
                {
                    return userArrayList.indexOf(user);
                }
            }
        }
        return 0;
    }

    @Override
    public void onClick(View view)
    {
        int resourceId = view.getId();

        if(resourceId == R.id.btnEmployee)
        {
            Intent intent = new Intent(this, EmployeeOperation.class);
            startActivity(intent);
            finish();
        }
        else if(resourceId == R.id.btnLogin)
        {
            if(!edtUsername.getText().toString().equals("") && !edtPassword.getText().toString().equals("") && selectRole.getSelectedItemPosition() != 0)
            {
                if(Utils.hasData.equals("1"))
                {
                    if(isUsernameExist())
                    {
                        for (User user : userArrayList)
                        {
                            if(edtUsername.getText().toString().trim().equals(user.getUsername()) &&
                                    edtPassword.getText().toString().trim().equals(user.getPassword()) &&
                                            userArrayList.get(checkRole()).getRole().equals(selectRole.getSelectedItem().toString().trim()))
                            {
                                Intent intent = new Intent(this, CustomerOperation.class);
                                startActivity(intent);
                                finish();
                            }
                            else if(edtUsername.getText().toString().trim().equals(user.getUsername()) &&
                                            edtPassword.getText().toString().trim().equals(user.getPassword()) &&
                                                    userArrayList.get(checkRole()).getRole().equals(selectRole.getSelectedItem().toString().trim()))
                            {
                                Intent intent = new Intent(this, EmployeeOperation.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(this, "Incorrect Credentials !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else if(!isUsernameExist())
                    {
                        Toast.makeText(this, "Username is not registered in our database !", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(Utils.hasData.equals("0"))
                {
                    Toast.makeText(this, "No User Exist", Toast.LENGTH_SHORT).show();
                }
            }
            else if(!edtUsername.getText().toString().equals("") && !edtPassword.getText().toString().equals("") && selectRole.getSelectedItemPosition() == 0)
            {
                Toast.makeText(this, "Please select your role", Toast.LENGTH_SHORT).show();
            }
        }
    }
}