package com.tangweizhi.tedtalk;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.jzvd.JZVideoPlayer;

public class MainActivity extends AppCompatActivity {
    TextView featureIcon;
    TextView surpriseIcon;
    TextView discoverIcon;
    TextView selfIcon;
    TextView searchIcon;
    TextView featureText;
    TextView surpriseText;
    TextView discoverText;
    TextView selfText;
    TextView searchText;
    Typeface iconFont;
    LinearLayout feature;
    LinearLayout surprise;
    LinearLayout discover;
    LinearLayout self;
    LinearLayout search;
    Fragment featureFragment;
    Fragment surpriseFragment;
    Fragment discoverFragment;
    Fragment selfFragment;
    Fragment searchFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAllView();
        initIcon();
        initFragment();
        initFeature();
        setBtnListener();
    }

    private void initFeature() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,featureFragment);
        fragmentTransaction.commit();
        changeFeatureColor(true);
    }

    private void initFragment() {
        featureFragment = new FeatureFragment();
        surpriseFragment = new SurpriseFragment();
        discoverFragment = new DiscoverFragment();
        selfFragment = new SelfFragment();
        searchFragment = new SearchFragment();
    }

    private void setBtnListener() {
        feature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabBarColor("feature");
                replaceFragment(featureFragment);
            }
        });
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabBarColor("surprise");
                replaceFragment(surpriseFragment);
            }
        });
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabBarColor("discover");
                replaceFragment(discoverFragment);
            }
        });
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabBarColor("self");
                replaceFragment(selfFragment);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeTabBarColor("search");
               replaceFragment(searchFragment);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }

    private void changeFeatureColor(Boolean bol){
        if (bol){
            featureIcon.setTextColor(getResources().getColor(R.color.colorRed));
            featureText.setTextColor(getResources().getColor(R.color.colorRed));
        }else {
            featureIcon.setTextColor(getResources().getColor(R.color.colorGray));
            featureText.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void changeSurpriseColor(Boolean bol){
        if (bol){
            surpriseIcon.setTextColor(getResources().getColor(R.color.colorRed));
            surpriseText.setTextColor(getResources().getColor(R.color.colorRed));
        }else {
            surpriseIcon.setTextColor(getResources().getColor(R.color.colorGray));
            surpriseText.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void changeDiscoverColor(Boolean bol){
        if (bol){
            discoverIcon.setTextColor(getResources().getColor(R.color.colorRed));
            discoverText.setTextColor(getResources().getColor(R.color.colorRed));
        }else {
            discoverIcon.setTextColor(getResources().getColor(R.color.colorGray));
            discoverText.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void changeSelfColor(Boolean bol){
        if (bol){
            selfIcon.setTextColor(getResources().getColor(R.color.colorRed));
            selfText.setTextColor(getResources().getColor(R.color.colorRed));
        }else {
            selfIcon.setTextColor(getResources().getColor(R.color.colorGray));
            selfText.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void changeSearchColor(Boolean bol){
        if (bol){
            searchIcon.setTextColor(getResources().getColor(R.color.colorRed));
            searchText.setTextColor(getResources().getColor(R.color.colorRed));
        }else {
            searchIcon.setTextColor(getResources().getColor(R.color.colorGray));
            searchText.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }

    private void changeTabBarColor(String name){
        switch (name){
            case "feature":
                changeFeatureColor(true);
                changeDiscoverColor(false);
                changeSearchColor(false);
                changeSurpriseColor(false);
                changeSelfColor(false);
                break;
            case "surprise":
                changeFeatureColor(false);
                changeDiscoverColor(false);
                changeSearchColor(false);
                changeSurpriseColor(true);
                changeSelfColor(false);
                break;
            case "discover":
                changeFeatureColor(false);
                changeDiscoverColor(true);
                changeSearchColor(false);
                changeSurpriseColor(false);
                changeSelfColor(false);
                break;
            case "self":
                changeFeatureColor(false);
                changeDiscoverColor(false);
                changeSearchColor(false);
                changeSurpriseColor(false);
                changeSelfColor(true);
                break;
            case "search":
                changeFeatureColor(false);
                changeDiscoverColor(false);
                changeSearchColor(true);
                changeSurpriseColor(false);
                changeSelfColor(false);
                break;
        }
    }

    private void initIcon() {
        featureIcon.setTypeface(iconFont);
        surpriseIcon.setTypeface(iconFont);
        discoverIcon.setTypeface(iconFont);
        selfIcon.setTypeface(iconFont);
        searchIcon.setTypeface(iconFont);
    }

    private void initAllView() {
        iconFont = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
        featureIcon = findViewById(R.id.feature_icon);
        surpriseIcon = findViewById(R.id.surprise_icon);
        discoverIcon = findViewById(R.id.discover_icon);
        selfIcon = findViewById(R.id.self_icon);
        searchIcon = findViewById(R.id.search_icon);
        featureText = findViewById(R.id.feature_text);
        surpriseText = findViewById(R.id.surprise_text);
        discoverText = findViewById(R.id.discover_text);
        selfText = findViewById(R.id.self_text);
        searchText = findViewById(R.id.search_text);
        feature = findViewById(R.id.feature);
        surprise = findViewById(R.id.surprise);
        discover = findViewById(R.id.discover);
        self = findViewById(R.id.self);
        search = findViewById(R.id.search);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }
}
