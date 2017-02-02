package com.yvesis.flyers.core.storage;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by louly on 2017-02-02.
 */

public class FileManager {

    static Object lock;

    public static FileManager getInstance(Context context) {
        synchronized (lock){
            if(instance == null)
                instance = new FileManager(context);
            return instance;
        }
    }

    static FileManager instance;
    Context context;
    static {
        lock = new Object();
    }
    private FileManager(Context context){
        this.context = context;
    }
    public byte[] readAllBytes(String filename){
        synchronized (lock)
        {
            try {
                Log.i("fm",filename);
                File file= context.getFileStreamPath(filename);
                byte[] bytes = new byte[(int) file.length()];
                Log.i("fm read", String.valueOf(bytes.length));
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

                int n = inputStream.read(bytes,0, bytes.length);
                inputStream.close();
                Log.i("fm read", String.valueOf(n));
                return  bytes;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new byte[0];
        }

    }
    public boolean fileExists(String filename){

        return context.getFileStreamPath(filename).exists();
    }
    public boolean writeAllBytes(String filename, byte[] bytes){

        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            File file1= context.getFileStreamPath(filename);
            Log.i("fm write", filename +" :"+ String.valueOf(file1.length()));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }
}
