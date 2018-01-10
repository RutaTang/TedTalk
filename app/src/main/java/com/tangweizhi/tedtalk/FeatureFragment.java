package com.tangweizhi.tedtalk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweizhi on 2018/1/9.
 */

public class FeatureFragment extends Fragment {
    ViewPager featurePager;
    List<View> viewList;
    List<Fragment> fragmentList;
    TextView featured;
    TextView popular;
    TextView china;
    FeaturedVFragment featuredVFragment;
    PopularVFragment popularVFragment;
    ChinaVFragment chinaVFragment;
    FragmentManager fragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentManager = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature,null);
        findView(view);
        initVFragment();
        initPager();
        setBtnListener();
        return view;
    }

    private void initVFragment() {
        featuredVFragment = new FeaturedVFragment();
        popularVFragment = new PopularVFragment();
        chinaVFragment = new ChinaVFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(featuredVFragment);
        fragmentList.add(popularVFragment);
        fragmentList.add(chinaVFragment);
    }

    private void setBtnListener() {
        featured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featurePager.setCurrentItem(0);
            }
        });
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featurePager.setCurrentItem(1);
            }
        });
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featurePager.setCurrentItem(2);
            }
        });
    }

    private void initPager() {
        getViews();
        featurePager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

        });
        featurePager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        featurePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        featured.setTextColor(getResources().getColor(R.color.colorBlack));
                        popular.setTextColor(getResources().getColor(R.color.colorRed));
                        china.setTextColor(getResources().getColor(R.color.colorRed));
                        break;
                    case 1:
                        featured.setTextColor(getResources().getColor(R.color.colorRed));
                        popular.setTextColor(getResources().getColor(R.color.colorBlack));
                        china.setTextColor(getResources().getColor(R.color.colorRed));
                        break;
                    case 2:
                        featured.setTextColor(getResources().getColor(R.color.colorRed));
                        popular.setTextColor(getResources().getColor(R.color.colorRed));
                        china.setTextColor(getResources().getColor(R.color.colorBlack));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getViews() {
        View featuredPager = getLayoutInflater().inflate(R.layout.viewpager_featured,null);
        View popularPager = getLayoutInflater().inflate(R.layout.viewpager_popular,null);
        View chinaPager = getLayoutInflater().inflate(R.layout.viewpager_china,null);
        viewList = new ArrayList<>();
        viewList.add(featuredPager);
        viewList.add(popularPager);
        viewList.add(chinaPager);

    }

    private void findView(View view) {
        featurePager = view.findViewById(R.id.feature_pager);
        featured = view.findViewById(R.id.featured);
        popular = view.findViewById(R.id.popular);
        china = view.findViewById(R.id.china);
    }
}
