package com.example.carrental.GetterSetter;

public class User
{
    private String uniqueId;
    private String Firstname, Lastname, Username, Password, Role;

    public String getFirstname() { return Firstname; }
    public void setFirstname(String firstname) { Firstname = firstname; }

    public String getLastname() { return Lastname; }
    public void setLastname(String lastname) { Lastname = lastname; }

    public String getUsername() { return Username; }
    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password; }

    public String getRole() { return Role; }
    public void setRole(String role) { Role = role; }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}