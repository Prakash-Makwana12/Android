package com.prakash.prakash_makwana;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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


public class ProductList extends Fragment
{
    TextView valPname, valPprice, valPQty;
    ImageView pImage;
    DBHelper db;
    ListView mylist;
    SimpleCursorAdapter cursorAdapter;
    Cursor productCursor;
    Cursor updatedDataCursor;
    String uid;
    SearchView searchView;
    public static ProductList newInstance()
    {
        return new ProductList();
    }

    public ProductList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.product_list, container, false);
        Intent i = getActivity().getIntent();
        uid = i.getStringExtra("userId");
        // Inflate the layout for this fragment
        searchView = (SearchView)view.findViewById(R.id.searchView) ;
        mylist = (ListView)view.findViewById(R.id.listView);
        db = new DBHelper(getContext());
        productCursor = db.getProductData();
        System.out.println("++++++++++++++++Image :"+db.PROD_IMAGE);
        String[] from = {db.PROD_IMAGE,db.PROD_NAME,db.PROD_PRICE,db.PROD_QUANTITY};
        int[] to = {R.id.imageView2,R.id.name,R.id.price,R.id.qty};
        cursorAdapter = new SimpleCursorAdapter(getContext(),R.layout.my_product,productCursor,from,to,0);
        mylist.setAdapter(cursorAdapter);


        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                valPname = (TextView)view.findViewById(R.id.name);

                db = new DBHelper(getContext());
                Product product = db.getProduct(valPname.getText().toString());


                valPprice = (TextView)view.findViewById(R.id.price);
                valPQty = (TextView)view.findViewById(R.id.qty);
                pImage = (ImageView)view.findViewById(R.id.imageView2);

            //  int image = pImage.getId();

              //  System.out.println("Image code=" +image);


                Intent intent = new Intent(getContext(),ProductDetail.class);
                intent.putExtra("p_name",product.product_name);
                intent.putExtra("p_price",product.product_price);
                intent.putExtra("p_qty",product.product_quantity);
                intent.putExtra("p_img",product.product_image);
                intent.putExtra("user",uid);

              //  intent.putExtra("p_img",image);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updatedDataCursor = db.getSearchProduct(newText);
                cursorAdapter.swapCursor(updatedDataCursor);
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
        updatedDataCursor = db.getProductData();
        cursorAdapter.swapCursor(updatedDataCursor);
        cursorAdapter.notifyDataSetChanged();
    }

}
