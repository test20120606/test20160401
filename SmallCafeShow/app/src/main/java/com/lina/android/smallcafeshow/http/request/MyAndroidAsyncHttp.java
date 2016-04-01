package com.lina.android.smallcafeshow.http.request;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Administrator on 2016/3/16.
 */
public class MyAndroidAsyncHttp {
    public static AsyncHttpClient asyncHttpClient;
    public static void init(){
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setTimeout(180*1000);
    }
}
