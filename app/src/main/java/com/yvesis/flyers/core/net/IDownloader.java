package com.yvesis.flyers.core.net;

/**
 * Created by louly on 2017-03-07.
 */
public interface IDownloader {
    void getContent(String url, IDownloaderCallback callback);

    public interface IDownloaderCallback<T>
    {

        void onContentDownloaded(String htmlContent);
        <T> void onContentParsed(String htmlContent, T moodle);
        void oneDownloadFailed(String htmlContent);
    }
}
