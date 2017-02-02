package com.yvesis.flyers.core.net;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yvesis.flyers.core.models.FlyerCategory;
import com.yvesis.flyers.core.models.FlyerItem;
import com.yvesis.flyers.core.models.FlyerShop;
import com.yvesis.flyers.core.parsers.BasicFlyerParser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by louly on 2017-01-30.
 */

public class FlyersDownloader extends Downloader {

    public  FlyersDownloader(String baseUrl){
        super(baseUrl);

    }
    @Override
    public void getContent(String url, Downloader.IDownloaderCallback callback) {

        this.callback = callback;
        this.execute(url);


    }

    @Override
    protected String doInBackground(String... urls) {


       try {
            String url = String.format("%s/%s", baseUrl,urls[0]);
           // URL pour tests
            String maxiUrl ="http://flyers.maxi.ca/flyers/maxi-weeklyflyercq5/grid_view/210003?type=2&flyer_type_name=weeklyflyercq5%3Bhide%3Dflyer%2Cweeklyflyer%3Blocale%3Dfr%3Bstore_code%3D8906";
            String superCUrl = "http://ecirculaire.superc.ca/flyers/superc-circulaire/grid_view/212151?locale=fr&postal_code=h4l3h4&store_code=5928&type=2";

           url = superCUrl;//maxiUrl;//
            Log.i("url",url);

            String content = super.getDocument(url).html();
            callback.onContentDownloaded(content);
            return content;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        BasicFlyerParser basicParser = new BasicFlyerParser();
        callback.onContentParsed(s, basicParser.parse(s));
    }


}
