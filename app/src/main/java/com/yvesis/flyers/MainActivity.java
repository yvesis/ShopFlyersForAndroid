package com.yvesis.flyers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yvesis.flyers.core.models.FlyerItem;
import com.yvesis.flyers.core.models.FlyerShop;
import com.yvesis.flyers.core.net.Downloader;
import com.yvesis.flyers.core.net.FlyerContentDownloader;
import com.yvesis.flyers.core.net.FlyersDownloader;
import com.yvesis.flyers.core.storage.FileManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listview;
    private ShopFlyerItemsAdapter flyerItemAdapter;
    private RecyclerView.LayoutManager lvLayout;
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private FlyerShop shop;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items = new ArrayList<>();

        listview = (RecyclerView) findViewById(R.id.listview);
        listview.setHasFixedSize(true);
        lvLayout = new LinearLayoutManager(this);
        listview.setLayoutManager(lvLayout);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        //flyerItemAdapter = new ShopFlyerItemsAdapter(null,android.R.layout.simple_list_item_1);
        //listview.setAdapter(flyerItemAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRefresh);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refresh();

            }
        });


    }

    private  void refresh(){
        final FlyersDownloader downloader = new FlyersDownloader("http://www.maxi.ca/fr_CA");
        downloader.getContent("flyers.pageview.banner@MAXI.storenum@8705.week@current.html", new Downloader.IDownloaderCallback() {
            @Override
            public void onContentDownloaded(String htmlContent) {

            }

            @Override
            public void onContentParsed(String htmlContent, Object shopFlyer) {

                if(shopFlyer != null){
                    shop = (FlyerShop)shopFlyer;
                    setTitle("Flyer : " +shop.getShopName());
                    updateList();
                }
                else{
                    final Downloader.IDownloaderCallback cb = this;
                    Snackbar.make(findViewById(R.id.activity_main),"Error, check your connection", Snackbar.LENGTH_LONG)
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // downloader.getContent("flyers.pageview.banner@MAXI.storenum@8705.week@current.html", cb);
                                }
                            })
                            .show();
                }

            }

            @Override
            public void oneDownloadFailed(String htmlContent) {
                Toast.makeText(getApplicationContext(),"Download failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private  void updateList(){
       /* items.clear();
        adapter.clear();

        for(FlyerCategory fc : shop){

            for(FlyerItem fi : fc){
                items.add(String.format("%s\n%s", fi.getName(), fi.getPrice()));
            }
        }*/

        flyerItemAdapter = new ShopFlyerItemsAdapter(this,shop);
        listview.setAdapter(flyerItemAdapter);

        flyerItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

