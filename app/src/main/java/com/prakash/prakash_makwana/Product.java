package com.prakash.prakash_makwana;

/**
 * Created by MEHUL on 29/04/2016.
 */
public class Product {

    String product_name;
    String product_type;
    String product_price;
    String product_quantity;
    int product_image;
    String product_desc;

    Product()
    {

        product_name = "";
        product_type = "";
        product_price = "";
        product_quantity = "";
        product_image = 0;
        product_desc = "";
    }
    Product( String product_name, String product_type, String product_price, String product_quantity,int product_image, String product_desc)
    {

        this.product_name = product_name;
        this.product_type = product_type;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_image = product_image;
        this.product_desc =product_desc;
    }
}
