package com.yvesis.flyers.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by louly on 2017-01-30.
 */

public final class FlyerShop implements Iterable<FlyerCategory> {

    private List<FlyerCategory> categoryList;
    private String shopName;
    private  int itemsCount;

    public String getShopName() {
        return shopName;
    }
    public FlyerShop(String shopName){
        this.shopName = shopName;
        categoryList = new ArrayList<>();
    }

    @Override
    public Iterator<FlyerCategory> iterator() {
        return categoryList.iterator();
    }

    public void addItemCategory(FlyerCategory category){
        categoryList.add(category);
    }
    public boolean removeItemCategory(FlyerCategory category){
        return categoryList.remove(category);
    }


    public int getItemsCount() {
        int itemsCount = 0;

        for(FlyerCategory fc : this)
            itemsCount+= fc.size();

        return itemsCount;
    }

    public FlyerItem getItem(int index){
        for(FlyerCategory fc : this)
        {
            if(index < fc.size()){
                return  fc.get(index);
            }
            else {
                index -= fc.size();
                if(index < 0) break;
            }
        }
        return  null;
    }

    public FlyerCategory getCategory(int index){
        return categoryList.get(index);
    }

    public String getItemCategory(int index) {
        for(FlyerCategory fc : this)
        {
            if(index < fc.size()){
                return  fc.getName();
            }
            else {
                index -= fc.size();
                if(index < 0) break;
            }
        }
        return  null;
    }
}
