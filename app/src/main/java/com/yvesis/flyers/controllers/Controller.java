package com.yvesis.flyers.controllers;

import android.app.Activity;
import android.content.Context;

/**
 * Created by louly on 2017-03-07.
 */
public abstract class Controller {

    protected int view_;
    protected Activity activity_;

    public Controller(Activity activity, int view){
        this.view_ = view;
        this.activity_ = activity;
        this.initializeComponents();
    }
    protected abstract void initializeComponents();
}
