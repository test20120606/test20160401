package com.lina.android.smallcafeshow.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lina.android.smallcafeshow.R;
import com.lina.android.smallcafeshow.view.adapter.FragAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{

    public ViewPager mViewPage;
    public FragAdapter fragAdapter;
    public Button button_Popular;
    public Button button_Recent;
    public  View view_popular;
    public  View view_recent;

    private View rootView = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewPage = (ViewPager) rootView.findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new PopularFragment());
        fragments.add(new RecentFragment());
        fragAdapter = new FragAdapter(getChildFragmentManager(), fragments);
        mViewPage.setAdapter(fragAdapter);
        mViewPage.setOnPageChangeListener(this);

        view_popular=rootView.findViewById(R.id.view_popular);
        view_recent=rootView.findViewById(R.id.view_recent);
        view_popular.setVisibility(View.VISIBLE);
        view_recent.setVisibility(View.INVISIBLE);

        button_Popular = (Button) rootView.findViewById(R.id.button_Popular);
        button_Recent = (Button) rootView.findViewById(R.id.button_Recent);
        button_Recent.setTextColor(getResources().getColor(R.color.color_button_press));
        button_Popular.setTextColor(getResources().getColor(R.color.color_button_normal));
        button_Popular.setOnClickListener(this);
        button_Recent.setOnClickListener(this);
    }


    @Override

    public void onClick(View v) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.button_Popular:
                mViewPage.setCurrentItem(0);
                button_Recent.setTextColor(getResources().getColor(R.color.color_button_press));
                button_Popular.setTextColor(getResources().getColor(R.color.color_button_normal));
                view_popular.setVisibility(View.VISIBLE);
                view_recent.setVisibility(View.INVISIBLE);
                break;
            case R.id.button_Recent:
                mViewPage.setCurrentItem(1);
                button_Popular.setTextColor(getResources().getColor(R.color.color_button_press));
                button_Recent.setTextColor(getResources().getColor(R.color.color_button_normal));
                view_popular.setVisibility(View.INVISIBLE);
                view_recent.setVisibility(View.VISIBLE);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            view_popular.setVisibility(View.VISIBLE);
            view_recent.setVisibility(View.INVISIBLE);
            button_Recent.setTextColor(getResources().getColor(R.color.color_button_press));
            button_Popular.setTextColor(getResources().getColor(R.color.color_button_normal));
        }else{
            view_popular.setVisibility(View.INVISIBLE);
            view_recent.setVisibility(View.VISIBLE);
            button_Popular.setTextColor(getResources().getColor(R.color.color_button_press));
            button_Recent.setTextColor(getResources().getColor(R.color.color_button_normal));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
