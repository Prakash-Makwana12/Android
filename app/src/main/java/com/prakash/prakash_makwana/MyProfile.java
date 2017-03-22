package com.prakash.prakash_makwana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MyProfile extends Fragment {
    DBHelper db;
    User user;
    Button btnEdit;
    TextView valName;
    TextView valEmail;
    TextView valPassword;
    ImageView valImg;
    EditText txtName,txtMail,txtPass;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     @return A new instance of fragment MyProfile.
     */
    public static MyProfile newInstance()
    {
        return new MyProfile();
    }

    public MyProfile()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_profile, container, false);
        Intent extras = getActivity().getIntent();
        String text_value= extras.getStringExtra("userEmail");
        System.out.println("email "+text_value);
        db = new DBHelper(this.getContext());
         user = db.getUser(text_value);
        valImg = (ImageView)view.findViewById(R.id.imageView);
        valImg.setImageResource(user.image);
        txtName =(EditText)view.findViewById(R.id.editText) ;
        txtMail =(EditText)view.findViewById(R.id.editText2) ;
        txtPass =(EditText)view.findViewById(R.id.editText3) ;

        txtName.setVisibility(View.GONE);
        txtMail.setVisibility(View.GONE);
        txtPass.setVisibility(View.GONE);

        btnEdit = (Button)view.findViewById(R.id.btnEdit);

        valName = (TextView)view.findViewById(R.id.txtName);
        valEmail = (TextView)view.findViewById(R.id.txtMail);
        valPassword = (TextView)view.findViewById(R.id.txtPassword);

        final String oldMail = user.emailAddress;
        valName.setText("    Name : "+user.name);
        valEmail.setText("    Email : "+user.emailAddress);
        valPassword.setText("Password : "+user.password);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(btnEdit.getText().equals("Edit"))
                {
                    txtName.setVisibility(View.VISIBLE);
                    txtMail.setVisibility(View.VISIBLE);
                    txtPass.setVisibility(View.VISIBLE);
                    txtName.setText(user.name);
                    txtMail.setText(user.emailAddress);
                    txtPass.setText(user.password);
                    valName.setVisibility(View.GONE);
                    valEmail.setVisibility(View.GONE);
                    valPassword.setVisibility(View.GONE);
                    btnEdit.setText("Save");
                }
                else if(btnEdit.getText().equals("Save"))
                {
                    System.out.println("in SaveButton");
                    db.updateUser(txtName.getText().toString(),txtMail.getText().toString(),txtPass.getText().toString(),oldMail);
                }


            }
        });

        return view;
    }
}