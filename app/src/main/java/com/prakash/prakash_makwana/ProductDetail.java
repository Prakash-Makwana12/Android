package com.prakash.prakash_makwana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetail extends AppCompatActivity
{
    Button btnAddCart;
    ImageView imageView;
    TextView valPname,valPprice, valPQty;
    DBHelper db;
    String  userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        db= new DBHelper(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        userId = extras.getString("user");
        btnAddCart = (Button)findViewById(R.id.btnAddCart);

       imageView = (ImageView)findViewById(R.id.imageView3);
        valPname = (TextView)findViewById(R.id.textView);
        valPprice  = (TextView)findViewById(R.id.textView2);
        valPQty  = (TextView)findViewById(R.id.textView3);

        final Intent intent = getIntent();

        valPname.setText("Name : "+intent.getStringExtra("p_name"));
        valPprice.setText("Price : $ "+intent.getStringExtra("p_price"));
        valPQty.setText("Quantity : "+intent.getStringExtra("p_qty"));
        imageView.setImageResource(intent.getIntExtra("p_img",0));

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                db.addToCart(userId,intent.getStringExtra("p_name"),intent.getStringExtra("p_price"),intent.getIntExtra("p_img",0));


                    Toast.makeText(ProductDetail.this,"Added to cart.",Toast.LENGTH_LONG).show();
                    finish();
            }
        });

    }
}
