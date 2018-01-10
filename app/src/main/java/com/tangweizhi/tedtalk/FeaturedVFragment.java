package com.tangweizhi.tedtalk;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by tangweizhi on 2018/1/9.
 */

public class FeaturedVFragment extends Fragment {

    List<HashMap<String,String>> data;
    FragmentManager fragmentManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        getFeatureData();
    }

    private void getFeatureData() {
        data = new ArrayList<>();
        HashMap<String,String> item = new HashMap<>();
        item.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/d439c4fd8bbfec0e444b820e7e473a09373e5816_2880x1620.jpg?cb=20160511&h=500&quality=90&w=");
        item.put("Auth","Touria EI Glaoui");
        item.put("Description","How the community of tiny single-cell organisms living inside our bodies have a huge — and largely unexplored — role in our health");
        HashMap<String,String> item2 = new HashMap<>();
        item2.put("Cover","https://pi.tedcdn.com/r/pf.tedcdn.com/images/playlists/using-art-history-to-examine-modern-day_2262284826_1200x627.jpg?cb=20160511&h=500&quality=90&w=");
        item2.put("Auth","Stewart");
        item2.put("Description","Mammoths resurrected, geoengineering and other thoughts from a futurist");
        HashMap<String,String> item3 = new HashMap<>();
        item3.put("Cover","https://pi.tedcdn.com/r/talkstar-photos.s3.amazonaws.com/uploads/2692ed2e-7b01-44fc-8de4-b8522baa13e1/SueJayeJohnson_2017S-embed.jpg?quality=63&w=512");
        item3.put("Auth","Sue Jaye");
        item3.put("Description","What we don't teach kids about sex");
        HashMap<String,String> item4 = new HashMap<>();
        item4.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/7e282e3b442c167b2993f0ef4a51d5e641174c1d_2880x1620.jpg?quality=63&w=512");
        item4.put("Auth","Michael");
        item4.put("Description","How we can make the world a better place by 2030");
        data.add(item);
        data.add(item2);
        data.add(item3);
        data.add(item4);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_featured,null);
        View videoView = inflater.inflate(R.layout.featured_video,null);
        JZVideoPlayerStandard jzVideoPlayerStandard = videoView.findViewById(R.id.featured_player);
        jzVideoPlayerStandard.setUp("http://v4.music.126.net/20180110211121/af420542c33075ef5b0452ada1fd86d8/web/cloudmusic/ICEwICQwIGAgICAxMGEyMA==/mv/5779389/bfc379a2a065c4a9a83dccf559624b76.mp4",JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
        Picasso.with(getContext()).load("https://pi.tedcdn.com/r/s3.amazonaws.com/talkstar-photos/uploads/9331c579-8948-4895-b07a-c515ea48e815/MindyScheier_2017S-embed.jpg?cb=20160613&quality=60&u=&w=1400").into(jzVideoPlayerStandard.thumbImageView);
        ListView listView = view.findViewById(R.id.featured_list);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.addHeaderView(videoView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                @SuppressLint("ViewHolder") View viewAd = getLayoutInflater().inflate(R.layout.featured_list,null);
                HashMap<String,String> item = data.get(position);
                ImageView cover = viewAd.findViewById(R.id.cover);
                TextView title = viewAd.findViewById(R.id.title);
                TextView decription = viewAd.findViewById(R.id.description);
                Typeface iconFont = Typeface.createFromAsset(getActivity().getAssets(),"iconfont/iconfont.ttf");
                TextView rightIcon = viewAd.findViewById(R.id.right_icon);
                rightIcon.setTypeface(iconFont);
                Picasso.with(getContext()).load(item.get("Cover")).into(cover);
                title.setText(item.get("Auth"));
                decription.setText(item.get("Description"));
                ConstraintLayout featuredContainer = viewAd.findViewById(R.id.featured_container);
                featuredContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.rightout_to_leftin,R.anim.rightin_to_leftout);
                        FeatureVideoDetailFragment featureVideoDetailFragment = new FeatureVideoDetailFragment();
                        fragmentTransaction.replace(R.id.fragmentContainer,featureVideoDetailFragment);
                        fragmentTransaction.commit();
                    }
                });
                return viewAd;
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
