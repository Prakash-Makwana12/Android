package com.prakash.prakash_makwana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MEHUL on 29/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static String name = "";
    //  Course courses = new Course();
    //  ArrayList<Course> myCourses = new ArrayList<>();


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userdb";
    private static final String TABLE_NAME = "prakash";
    private static final String _id = "_id";
    public  static final String username = "username";
    public  static final String password = "password";
    public  static final String email = "email";
    public static final String user_img = "user_img";


    private static final String PRODUCT_TABLE_NAME = "PRODUCTS";
    public   final String PROD_NAME = "PROD_NAME";
    public   final String PROD_TYPE = "PROD_TYPE";
    public   final String PROD_PRICE = "PROD_PRICE";
    public   final String PROD_QUANTITY = "PROD_QUANTITY";
    public   final String PROD_IMAGE = "PROD_IMAGE";
    public   final String PROD_DESCRIPTION = "PROD_DESCRIPTION";

    private static final String CART_TABLE_NAME = "CART";
    public   final String C_USERID = "C_USERID";
    public   final String C_PROD_NAME = "C_PROD_NAME";
    public   final String C_PROD_PRICE = "C_PROD_PRICE";
    public   final String C_PROD_IMAGE = "C_PROD_IMAGE";


    static  final String cart_table = "create table cart(_id integer primary key autoincrement,C_USERID text, C_PROD_NAME text,C_PROD_PRICE text,C_PROD_IMAGE text)";
    static  final String create_table = "create table prakash (ID integer primary key autoincrement, USERNAME text,EMAIL text, PASSWORD text,USER_IMG text)";
    // static  final String course_table = "create table course (_id integer primary key autoincrement, COURSENAME text, COURSEGRADE text)";
    static  final String product_table = "create table PRODUCTS (_id integer primary key autoincrement, PROD_NAME text, PROD_TYPE text,PROD_PRICE text,PROD_QUANTITY text,PROD_IMAGE text, PROD_DESCRIPTION text)";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        System.out.println("**********IN onCreate************** ");


        db.execSQL(create_table);
        System.out.println("*****User table created.*********");
        db.execSQL(product_table);
        System.out.println("***** product_table created.*********");

        db.execSQL(cart_table);
        System.out.println("***** cart_table created.*********");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }
    public void addToCart(String uid, String pname, String price, int img)

    {
        System.out.println("************ IN addCart() **************"+uid);
        System.out.println("************ IN addCart() **************"+pname);
        System.out.println("************ IN addCart() **************"+price);
        System.out.println("************ IN addCart() **************"+img);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(C_USERID,uid);
        value.put(C_PROD_NAME,pname);
        value.put(C_PROD_PRICE,price);
        value.put(C_PROD_IMAGE,img);


        db.insert(CART_TABLE_NAME, null, value);
        db.close();
        System.out.println("************ Data Inserted **************");
    }
    public Cursor getCartData(String uid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+CART_TABLE_NAME+" where C_USERID = "+uid,null);

        System.out.println("****************************cart Count"+cursor.getCount());
        return  cursor;

    }

    public void registerUser(User user)
    {
        System.out.println("************Inserting Data **************");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(username, user.name);
        values.put(email,user.emailAddress);
        values.put(password,user.password);
        values.put(user_img,user.image);
        db.insert(TABLE_NAME, null, values);
        db.close();
        System.out.println("************ Data Inserted **************");
    }
    public String getSingleEntry(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,"email=?",new String[]{userName},null,null,null);

        if (cursor.getCount() <1){
            cursor.close();
            return "NOT EXIST";

        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return  password;

    }
    public User getUser(String email)
    {
        User user = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("prakash",null,"email=?",new String[]{email},null,null,null);

        cursor.moveToFirst();
        user.name = cursor.getString(cursor.getColumnIndex("USERNAME"));
        user.emailAddress = cursor.getString(cursor.getColumnIndex("EMAIL"));
        user.password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        user.image = cursor.getInt(cursor.getColumnIndex("USER_IMG"));
        cursor.close();
        return  user;
    }
    public void updateUser(String name,String mail,String pass, String oldMail)
    {
      //  DatabaseCreator dbcreator=new DatabaseCreator(context);
        SQLiteDatabase sqdb=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(username,name);
        values.put(email,mail);
        values.put(password,pass);
       sqdb.update(TABLE_NAME,values,email+ "= ?",new String[]{oldMail} );
       // int id = sqdb.update(TABLE_NAME,values,"email="+oldMail,null);
    }

    public void addProduct(Product product)
    {
        System.out.println("************ IN addProduct() **************");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("PROD_NAME",product.product_name);
        value.put("PROD_TYPE",product.product_type);
        value.put("PROD_PRICE",product.product_price);
        value.put("PROD_QUANTITY",product.product_quantity);
        value.put("PROD_IMAGE",product.product_image);
        value.put("PROD_DESCRIPTION",product.product_desc);
        db.insert(PRODUCT_TABLE_NAME, null, value);
        db.close();
        System.out.println("************ Data Inserted **************");

    }
    public String getUserData(String email )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,"email=?",new String[]{email},null,null,null);

        if (cursor.getCount() <1){
            cursor.close();
            return "NOT EXIST";

        }
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex("ID"));
        cursor.close();
        return  id;

    }
    public Cursor getProductData( )
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+PRODUCT_TABLE_NAME,null);
        System.out.println("****************************product Count"+cursor.getCount());
        return  cursor;

    }

    public Product getProduct(String pname)
    {
        Product product = new Product();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE_NAME,null,"PROD_NAME=?",new String[]{pname},null,null,null);

        cursor.moveToFirst();
        product.product_name = cursor.getString(cursor.getColumnIndex("PROD_NAME"));
        product.product_price = cursor.getString(cursor.getColumnIndex("PROD_PRICE"));
        product.product_quantity = cursor.getString(cursor.getColumnIndex("PROD_QUANTITY"));
        product.product_image = cursor.getInt(cursor.getColumnIndex("PROD_IMAGE"));
        cursor.close();
        return  product;
    }

    public Cursor getSearchProduct(String txtsearch ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+PRODUCT_TABLE_NAME+" where PROD_NAME LIKE '"+txtsearch+"%'",null);
        return  cursor;

    }
    public Cursor getSearchCart(String txtsearch ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+CART_TABLE_NAME+" where C_PROD_NAME LIKE '"+txtsearch+"%'",null);
        return  cursor;

    }
    public void truncateProductTable()
    {
        String query = "delete from "+PRODUCT_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }


}

