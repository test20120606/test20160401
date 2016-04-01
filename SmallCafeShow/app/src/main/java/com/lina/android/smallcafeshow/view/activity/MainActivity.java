package com.lina.android.smallcafeshow.view.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.lina.android.smallcafeshow.R;
import com.lina.android.smallcafeshow.view.fragment.HomeFragment;
import com.lina.android.smallcafeshow.view.fragment.MeFragment;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MEDIA_TYPE_VIDEO = 1;
    public FragmentManager fragmentManager;
    private HomeFragment fragment_home;
    private MeFragment fragment_me;


    private Button button_home;
    private ImageButton button_discover;
    private Button button_me;
//    private LinearLayout bottom_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bottom_layout= (LinearLayout) findViewById(R.id.bottom_layout);
        button_home= (Button) findViewById(R.id.button_home);
        button_discover= (ImageButton) findViewById(R.id.button_discover);
        button_me= (Button) findViewById(R.id.button_me);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(fragment_home ==null) {
            fragment_home = new HomeFragment();
        }
        fragmentTransaction.replace(R.id.container_fragment, fragment_home);
        fragmentTransaction.commit();

        button_home.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_bottom_menu_home_normal, 0, 0);
        button_me.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_bottom_menu_me_enable, 0, 0);
        button_home.setTextColor(getResources().getColor(R.color.color_button_normal));
        button_me.setTextColor(getResources().getColor(R.color.color_button_press));

        button_home.setOnClickListener(this);
        button_discover.setOnClickListener(this);
        button_me.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         fragmentManager = getSupportFragmentManager();
         FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.button_home:
                if(fragment_home==null) {
                    fragment_home = new HomeFragment();
                }
                transaction.replace(R.id.container_fragment, fragment_home);
                transaction.commit();
                button_home.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_bottom_menu_home_normal, 0, 0);
                button_me.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.home_bottom_menu_me_enable,0,0);
                button_home.setTextColor(getResources().getColor(R.color.color_button_normal));
                button_me.setTextColor(getResources().getColor(R.color.color_button_press));
                break;
            case R.id.button_discover:
                Intent intent=new Intent();
                intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputFileUri(MEDIA_TYPE_VIDEO));
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                startActivityForResult(intent, MEDIA_TYPE_VIDEO);
                break;
            case R.id.button_me:
                if(fragment_me==null) {
                    fragment_me = new MeFragment();
                }
                transaction.replace(R.id.container_fragment,fragment_me);
                transaction.commit();
                button_home.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_bottom_menu_home_enable, 0, 0);
                button_me.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_bottom_menu_me_normal, 0, 0);
                button_home.setTextColor(getResources().getColor(R.color.color_button_press));
                button_me.setTextColor(getResources().getColor(R.color.color_button_normal));
                break;
        }
    }

    public Uri getOutputFileUri(int type){
        return Uri.fromFile(getOutputFile(type));
    }


    public File getOutputFile(int type){
        File file=null;
        String rootPath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
        file=new File(rootPath+File.separator+System.currentTimeMillis()+".mp4");
        return file;
    }


}
