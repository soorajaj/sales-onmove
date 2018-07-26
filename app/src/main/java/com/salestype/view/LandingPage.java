package com.salestype.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.salestype.R;
import com.salestype.listener.AlertListener;
import com.salestype.model.Login;
import com.salestype.model.StockDetail;
import com.salestype.singletonManager.ObjectFactory;
import com.salestype.utilites.UtilsSharedPrefrence;
import com.salestype.utilites.utility;
import com.salestype.view.fragments.CreateSales;
import com.salestype.view.fragments.Mysales;
import com.salestype.view.fragments.StockList;

import java.util.List;

public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,StockList.OnItemStartActivityListener,StockList.Loaddbdata {
    public List<StockDetail> mStockListArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mStockListArrayList = StockDetail.listAll(StockDetail.class);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Fragment fragment=new StockList();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_landing, fragment);
        transaction.commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            utility.showInformationAlertToButton(LandingPage.this, "Do you wand to exit. All unsaved data will be loos", new AlertListener() {
                @Override
                public void onSubmit() {
                    finish();
                    ObjectFactory.getInstance().getStockmanager(LandingPage.this).cleanup();
                }

                @Override
                public void onCancel() {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_page, menu);
        return true;
    }

    @Override
    public   boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public  boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Fragment fragment=new StockList();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_landing, fragment);
            transaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Fragment fragment=new CreateSales();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_landing, fragment);
            transaction.commit();
        }  else if (id == R.id.nav_manage) {
            utility.showInformationAlertToButton(LandingPage.this, "Are you sure you want to logout", new AlertListener() {
                @Override
                public void onSubmit() {
                    UtilsSharedPrefrence.clearPreference(LandingPage.this);
                    ObjectFactory.getInstance().getStockmanager(LandingPage.this).cleanup();
                    Intent intent=new Intent(LandingPage.this,LoginView.class);
                    startActivity(intent);
                }

                @Override
                public void onCancel() {

                }
            });
        }else if (id==R.id.nav_mysales){
            Fragment fragment=new Mysales();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_landing, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void Loaddata(int pos) {
        if (pos==1){
            Fragment fragment=new CreateSales();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_landing, fragment);
            transaction.commit();
        }else if (pos==2){
            Fragment fragment=new StockList();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_landing, fragment);
            transaction.commit();
            mStockListArrayList = StockDetail.listAll(StockDetail.class);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public List<StockDetail> Loaddata() {
        return mStockListArrayList;
    }

    @Override
    public void updatelist(int pos) {
       mStockListArrayList.get(pos).setSelected(true);
    }
}
