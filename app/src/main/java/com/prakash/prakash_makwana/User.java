package com.prakash.prakash_makwana;

/**
 * Created by MEHUL on 29/04/2016.
 */
public class User
{
    String name;
    String password;
    String emailAddress;
    int image;

    User()
    {
        name = "";
        password = "";
        emailAddress = "";
        image = 0;

    }
    User( String name, String password,String emailAddress, int image )
    {
        this.name = name;
        this.password = password;
        this.emailAddress =emailAddress;
        this.image = image;
    }
}
