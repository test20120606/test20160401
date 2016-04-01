package com.lina.android.smallcafeshow;

import android.app.Application;

import com.lina.android.smallcafeshow.http.request.MyAndroidAsyncHttp;
import com.lina.android.smallcafeshow.http.myimageloader.MyImageLoader;

/**
 * Created by Administrator on 2016/3/17.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyAndroidAsyncHttp.init();
        MyImageLoader.init(this);
    }
}
