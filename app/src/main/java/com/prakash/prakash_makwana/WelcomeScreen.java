package com.prakash.prakash_makwana;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomeScreen extends AppCompatActivity
{
    String text_value;
    String uname;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Bundle extras = getIntent().getExtras();
        text_value= extras.getString("userEmail");
        uname = extras.getString("userName");
        userId = extras.getString("userId");
        System.out.println("************USERID******"+userId);
        System.out.println("***************************Emamamam**********"+text_value);

        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome "+uname+" !");
        setSupportActionBar(toolbar);

        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);


        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());


        viewPager.setAdapter(pagerAdapter);

        // Setup the Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // By using this method the tabs will be populated according to viewPager's count and
        // with the name from the pagerAdapter getPageTitle()

        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
        tabLayout.setupWithViewPager(viewPager);
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0:
                    return ProductList.newInstance();
                case 1:
                    return MyCart.newInstance();
                case 2:
                    return MyProfile.newInstance();
                default:
                    return ProductList.newInstance();
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {

                case 0:
                    return "Produts";
                case 1:
                    return "My Cart";
                case 2:
                    return "My Profile";
                default:
                    return "Produts";
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         if (id == R.id.action_logout)
         {
            finish();
         }
        if(text_value.equalsIgnoreCase("admin@admin.com"))
        {

            if (id == R.id.action_addProduct)
            {

                Intent i = new Intent(getApplicationContext(),AddProduct.class);
                startActivity(i);
                return true;
            }

        }



        return super.onOptionsItemSelected(item);
    }

}
