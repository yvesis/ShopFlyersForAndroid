package com.yvesis.flyers.core.net;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class FlyerContentDownloader extends Downloader {

    byte[] output;
    public FlyerContentDownloader(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public void getContent(String url, IDownloaderCallback callback) {
        this.callback = callback;
        this.execute(url);
    }

    @Override
    protected String doInBackground(String... urls) {



            try {
                Connection.Response response = Jsoup.connect(urls[0]).ignoreContentType(true).execute();
                output = response.bodyAsBytes();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }




        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        this.callback.onContentParsed(s,output);
    }
}
