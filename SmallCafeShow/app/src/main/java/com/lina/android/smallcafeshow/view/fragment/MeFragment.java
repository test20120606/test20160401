package com.lina.android.smallcafeshow.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lina.android.smallcafeshow.R;
import com.lina.android.smallcafeshow.view.activity.FeedbackActivity;
import com.lina.android.smallcafeshow.view.activity.TermsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private SlidingMenu menu;
    private ImageView setButton;
    private TextView textView_cache;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        setButton = (ImageView) rootView.findViewById(R.id.setButton);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSlidingMenu();
        menu.findViewById(R.id.textView_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        menu.findViewById(R.id.textView_terms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsActivity.class);
                startActivity(intent);
            }
        });


        menu.findViewById(R.id.layout_clean).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder =new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure to clean cache?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_cache= (TextView) menu.findViewById(R.id.textView_cache);
                        textView_cache.setText("0");
                        Toast.makeText(getActivity(), "Cleaned", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.showMenu();
            }
        });
    }

    @Override
    public void onDestroyView() {
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        super.onDestroyView();
    }


    public void initSlidingMenu() {
        menu = new SlidingMenu(getActivity());
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffsetRes(R.dimen.menu_offset);
        menu.setAboveOffsetRes(R.dimen.above_Offset);
        menu.setMenu(R.layout.settings);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.55f);
        menu.setBackgroundColor(Color.BLACK);
        menu.setBehindScrollScale(0.0f);
        menu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
    }


}
