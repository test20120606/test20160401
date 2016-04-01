package com.lina.android.smallcafeshow.http.download;

import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Videodownload {
    private static final String TAG = "Videodownload";

    Executor executor = Executors.newSingleThreadExecutor();
    private IDownloadCallBack iDownloadCallBack;


    public interface IDownloadCallBack {
        void downCallBack(String filePath);
    }

    public void fun(final String url) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                download(url);
            }
        });
    }

    public void download(String strurl) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(strurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
            in = conn.getInputStream();
            out = new FileOutputStream(Environment.getExternalStorageDirectory()
                    + strurl.substring(strurl.lastIndexOf("/")));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();

            iDownloadCallBack.downCallBack(Environment.getExternalStorageDirectory()
                    + strurl.substring(strurl.lastIndexOf("/")));

        } catch (MalformedURLException e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public void fun1(IDownloadCallBack iDownloadCallBack) {
        this.iDownloadCallBack = iDownloadCallBack;
    }
}
