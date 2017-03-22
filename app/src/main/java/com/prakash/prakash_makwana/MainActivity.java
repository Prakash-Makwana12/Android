package com.prakash.prakash_makwana;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;
    EditText valEmail,valPass;
    DBHelper test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = new DBHelper(MainActivity.this);
      //  test.truncateProductTable();

        valEmail = (EditText)findViewById(R.id.txtEmail);
        valPass = (EditText)findViewById(R.id.txtPass);


        register = (Button) findViewById(R.id.btnRegister) ;
        login = (Button)findViewById(R.id.btnLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("in Login page");
                String email =  valEmail.getText().toString();
                String password = valPass.getText().toString();

                String storedPassword = test.getSingleEntry(email);
                System.out.println(storedPassword);

                if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    valEmail.setError("enter a valid email address");
                }

                else if(password.equals(storedPassword))
                {
                    User user = test.getUser(email);
                    String userId = test.getUserData(email);
                    Intent i = new Intent(getApplicationContext(),WelcomeScreen.class);
                    i.putExtra("userEmail",email);
                    i.putExtra("userName",user.name);
                    i.putExtra("userId",userId);

                    startActivity(i);

                }else {
                    Toast.makeText(MainActivity.this,"Username OR Password does not match",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
