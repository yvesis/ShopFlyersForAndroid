package com.yvesis.flyers.core.parsers;

import android.util.Log;

import com.yvesis.flyers.core.models.FlyerCategory;
import com.yvesis.flyers.core.models.FlyerItem;
import com.yvesis.flyers.core.models.FlyerShop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by louly on 2017-01-30.
 */

public class BasicFlyerParser implements IShopFlyerParser<FlyerShop> {
    @Override
    public FlyerShop parse(String content) {

        Document doc = Jsoup.parse(content);

        String shopName = doc.select("div.flatsheettopbar-menu-item.flatsheettopbar-location")
                             .select("div.flatsheettopbar-menu-item-title>p").text();
        FlyerShop maxi = new FlyerShop(shopName);
        Log.i("shop",shopName);
        Elements categories = doc.select("div.items-list>div:gt(0)");

        for(Element category : categories)
        {
            String categoryName = category.select("div.grid-view-heading>h3").text();
            //Log.i("category",categoryName);

            FlyerCategory flyerCategory = new FlyerCategory(categoryName);
            maxi.addItemCategory(flyerCategory);

            Elements items = category.select(".item");

            for(Element item : items){
                String price = item.select(".item-price.wishabi-offscreen>span:gt(0)").text();
                String imgUrl = item.select(".img-wrapper-cell>a>img").first().attr("src");
                //Log.i("img",imgUrl);
                FlyerItem flyerItem = new FlyerItem( item.select(".item-name").text(),price);
                flyerItem.setImageUrl(imgUrl);
                flyerCategory.add(flyerItem);
                Log.i("Item", flyerItem.toString());
            }
        }


        return maxi;
    }

    @Override
    public FlyerShop parseFile(String filename) {
        return null;
    }


}
