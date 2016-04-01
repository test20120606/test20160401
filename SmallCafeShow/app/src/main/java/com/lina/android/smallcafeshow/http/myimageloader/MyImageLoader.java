package com.lina.android.smallcafeshow.http.myimageloader;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MyImageLoader {
    public static ImageLoader universalimageloader;

    public static void init(Context context){
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .memoryCacheSizePercentage(20)
                .diskCacheFileCount(1000)
                .diskCacheSize(50 * 1024 * 1024)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        ImageLoader.getInstance().init(configuration);
        universalimageloader=ImageLoader.getInstance();
    }
}
