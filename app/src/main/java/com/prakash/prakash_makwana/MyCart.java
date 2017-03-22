package com.prakash.prakash_makwana;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MyCart extends Fragment
{

    TextView valPname, valPprice;
    ImageView pImage;
    DBHelper db;
    ListView mylist;
    SimpleCursorAdapter cursorAdapter;
    Cursor cartCursor;
    Cursor updatedCartCursor;
    String uid;
    SearchView cartSearch;
    public static MyCart newInstance() {
        return new MyCart();
    }

    public MyCart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.my_cart, container, false);
        Intent i = getActivity().getIntent();
        uid = i.getStringExtra("userId");
        System.out.println("Userid In My Cart:"+uid);

        // Inflate the layout for this fragment
        cartSearch = (SearchView)view.findViewById(R.id.searchView2) ;
        mylist = (ListView)view.findViewById(R.id.listView2);
        db = new DBHelper(getContext());
        cartCursor = db.getCartData(uid);
        System.out.println("In My Cart**************cursor count"+cartCursor.getCount());
       // System.out.println("++++++++++++++++Image :"+db.PROD_IMAGE);
        String[] from = {db.C_PROD_IMAGE,db.C_PROD_NAME,db.C_PROD_PRICE};
        int[] to = {R.id.imageView4,R.id.textView4,R.id.textView5};
        cursorAdapter = new SimpleCursorAdapter(getContext(),R.layout.cart_layout,cartCursor,from,to,0);
        mylist.setAdapter(cursorAdapter);


        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                valPname = (TextView)view.findViewById(R.id.textView4);

                db = new DBHelper(getContext());
                Product product = db.getProduct(valPname.getText().toString());


                valPprice = (TextView)view.findViewById(R.id.textView5);
              //  valPQty = (TextView)view.findViewById(R.id.qty);
                pImage = (ImageView)view.findViewById(R.id.imageView4);

                //  int image = pImage.getId();

                //  System.out.println("Image code=" +image);


                Intent intent = new Intent(getContext(),ProductDetail.class);
                intent.putExtra("p_name",product.product_name);
                intent.putExtra("p_price",product.product_price);
                intent.putExtra("p_qty",product.product_quantity);
                intent.putExtra("p_img",product.product_image);

                //  intent.putExtra("p_img",image);
                startActivity(intent);
            }
        });


        cartSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updatedCartCursor = db.getSearchCart(newText);
                cursorAdapter.swapCursor(updatedCartCursor);
                cursorAdapter.notifyDataSetChanged();
                return false;
            }
        });


        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updatedCartCursor = db.getCartData(uid);
        cursorAdapter.swapCursor(updatedCartCursor);
        cursorAdapter.notifyDataSetChanged();
    }
}
