package com.prakash.prakash_makwana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    User newUser;
    private Button register;
    EditText username;
    EditText valEmail;
    EditText pass;
    DBHelper reg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg = new DBHelper(Register.this);

        username = (EditText)findViewById(R.id.txtUser) ;
        valEmail = (EditText)findViewById(R.id.txtEmail) ;
        pass = (EditText)findViewById(R.id.txtPass) ;

        register = (Button) findViewById(R.id.btnRegister) ;
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(username.getText().toString().isEmpty() || valEmail.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"All Fields are mendatory!!!",Toast.LENGTH_LONG).show();
                }
                else if(valEmail.getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(valEmail.getText().toString()).matches())
            {
                valEmail.setError("enter a valid email address");
            }
                else
                {
                    newUser = new User();
                    newUser.name = username.getText().toString();
                    newUser.password = pass.getText().toString();
                    newUser.emailAddress = valEmail.getText().toString();
                    newUser.image =  R.drawable.male;

                    reg.registerUser(newUser);

                    Toast.makeText(getApplicationContext(),"Account Created Successfully",Toast.LENGTH_LONG).show();
                    System.out.println("Inserted value :"+username);

                    Intent intent = new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
