package com.yvesis.flyers.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.yvesis.flyers.R;
import com.yvesis.flyers.ShopFlyerItemsAdapter;
import com.yvesis.flyers.core.models.FlyerShop;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by louly on 2017-03-07.
 */

public final class MainController extends Controller {

    private RecyclerView listview;
    private ShopFlyerItemsAdapter flyerItemAdapter;
    private RecyclerView.LayoutManager lvLayout;
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private FlyerShop shop;

    public MainController(Activity activity, int view) {
        super(activity, view);
    }


    @Override
    protected void initializeComponents() {

        items = new ArrayList<>();
        listview = (RecyclerView) activity_.findViewById(R.id.listview);
        listview.setHasFixedSize(true);
        lvLayout = new LinearLayoutManager(activity_);
        listview.setLayoutManager(lvLayout);
    }


}
