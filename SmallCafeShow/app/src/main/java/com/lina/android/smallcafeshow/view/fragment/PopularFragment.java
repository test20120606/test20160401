package com.lina.android.smallcafeshow.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lina.android.smallcafeshow.R;
import com.lina.android.smallcafeshow.bean.BaseBean;
import com.lina.android.smallcafeshow.http.request.MyAndroidAsyncHttp;
import com.lina.android.smallcafeshow.view.activity.VideoActivity;
import com.lina.android.smallcafeshow.view.adapter.MyAdapter;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PopularFragment extends Fragment {

    private static final String TAG = "PopularFragment";
    private String jsonUrl = "http://api.mimic.mobi/video/videoInfo/getHotVideoList?start=0&count=39";
    private PullToRefreshListView listView;
    MyAdapter adapter;
    ArrayList<BaseBean> listB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);
        listView = (PullToRefreshListView) rootView.findViewById(R.id.listView);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listB=new ArrayList<BaseBean>();
        adapter=new MyAdapter(getActivity(),listB);
        adapter.setOnViewClick(new MyAdapter.OnViewClick() {
            @Override
            public void onClick(BaseBean bean) {
                Intent intent=new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("videoUrlKey",bean.videoUrl);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                getListAndroidAsyncHttp();
                ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true, false);
                startLayout.setPullLabel("下拉刷新");
                startLayout.setRefreshingLabel("正在加载中...");
                startLayout.setReleaseLabel("释放刷新");

            }
        });
        getListAndroidAsyncHttp();

    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView()");
        super.onDestroyView();
    }


    public void getListAndroidAsyncHttp() {
        MyAndroidAsyncHttp.asyncHttpClient.get(jsonUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "网络错误");
                listView.onRefreshComplete();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "网络成功");
                ArrayList<BaseBean> beanlist = new ArrayList<BaseBean>();//创建本地对象的集合
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);//获取jsonArray中的每个对象
                        BaseBean baseBean = new BaseBean();
                        baseBean.imageUrl = jsonObject2.getString("cover_addr");
                        baseBean.imageUser = jsonObject2.getString("icon");
                        baseBean.soundname = jsonObject2.getString("title");
                        baseBean.videoUrl=jsonObject2.getString("video_addr");
                        beanlist.add(baseBean);
                    }
                    listB.clear();
                    listB.addAll(beanlist);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listView.onRefreshComplete();

            }
        });
    }

}
