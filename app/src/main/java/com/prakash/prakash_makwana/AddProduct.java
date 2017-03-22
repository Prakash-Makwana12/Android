package com.prakash.prakash_makwana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Button btnAddProduct;
    EditText valProdName;
    EditText valProdPrice;
    EditText valProdQuantity;
    EditText valProdDesc;
    int valProdImg=0;
    DBHelper db;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        setControlId();


        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Mobile");
        categories.add("Computer");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(valProdName.getText().toString().isEmpty() || valProdPrice.getText().toString().isEmpty() || valProdQuantity.getText().toString().isEmpty() || valProdDesc.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"All Fields are mendatory!!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String prodType = "";
                    if(type == "Mobile")
                    {
                        System.out.println("++++++++++++++++++++in Mobile");
                        prodType = "mobile";
                        valProdImg =(R.drawable.mobile);

                    }
                    else if(type == "Computer")
                    {
                        prodType = "computer";
                        valProdImg= (R.drawable.computer);
                    }
                    Product newProduct =new Product();
                    newProduct.product_image = (valProdImg);
                    newProduct.product_name = valProdName.getText().toString();
                    newProduct.product_type = prodType;
                    newProduct.product_price = valProdPrice.getText().toString();
                    newProduct.product_quantity =valProdQuantity.getText().toString();
                    newProduct.product_desc = valProdDesc.getText().toString();

                    db.addProduct(newProduct);
                    // userInput = input.toString();
                    Toast.makeText(getApplicationContext(),"Product Added Successfully",Toast.LENGTH_LONG).show();
                   // System.out.println("Inserted value :"+username);
                   // Intent intent = new Intent(AddProduct.this,ProductList.class);
                   /// startActivity(intent);
                    finish();
                }
            }
        });


    }
    public void setControlId()
    {
        db = new DBHelper(AddProduct.this);

        btnAddProduct = (Button)findViewById(R.id.btnAdd);
        valProdName = (EditText)findViewById(R.id.prodName);
        valProdPrice = (EditText)findViewById(R.id.prodPrice);
        valProdQuantity = (EditText)findViewById(R.id.prodQuantity);
        valProdDesc = (EditText)findViewById(R.id.prodDesc);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
         type = parent.getItemAtPosition(position).toString();
        // imgID = (int)parent.getItemAtPosition(position);
     /*   actionList = new ArrayList<>();
        if(item.equalsIgnoreCase("action"))
        {
            // actionList.clear();
            for(Movie type : myList)
            {
                if(type.type.equalsIgnoreCase("action"))
                {
                    actionList.add(type);
                }

            }
        }
        else
        {
            {
                //  actionList.clear();
                for(Movie type : myList)
                {
                    if(type.type.equalsIgnoreCase("comedy"))
                    {

                        actionList.add(type);
                    }

                }
            }
        }
        listView = (ListView)findViewById(R.id.listView);
        adapter = new CustomListAdaptor(this,actionList);
        listView.setAdapter(adapter);
        */

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + type, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
