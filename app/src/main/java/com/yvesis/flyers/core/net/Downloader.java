package com.yvesis.flyers.core.net;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * Created by louly on 2017-01-30.
 */

public abstract class Downloader extends AsyncTask<String,Void,String> {

    protected IDownloaderCallback callback;
    protected String baseUrl;
    protected Downloader(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public interface IDownloaderCallback<T>
    {

        void onContentDownloaded(String htmlContent);
        <T> void onContentParsed(String htmlContent,T moodle);
        void oneDownloadFailed(String htmlContent);
    }

    public abstract void getContent(String url, IDownloaderCallback callback);

    protected Document getDocument(String url) throws IOException {

        //url ="http://www.google.ca";//http://flyers.maxi.ca/flyers/maxi-weeklyflyercq5/grid_view/210003?type=2&flyer_type_name=weeklyflyercq5%3Bhide%3Dflyer%2Cweeklyflyer%3Blocale%3Dfr%3Bstore_code%3D8906";
        //Log.i("url2",url);
        //String  uri = Uri.decode(url);
        return Jsoup.connect(url).get();
    }

}

