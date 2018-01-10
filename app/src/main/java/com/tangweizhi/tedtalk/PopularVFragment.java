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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by tangweizhi on 2018/1/9.
 */

public class PopularVFragment extends Fragment {
    List<HashMap<String,String>> data;
    FragmentManager fragmentManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFeatureData();
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    private void getFeatureData() {
        data = new ArrayList<>();
        HashMap<String,String> item = new HashMap<>();
        item.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/1af84b46c3b0b845018a4b86869d02a841a8cf62_2880x1620.jpg?quality=89&w=320");
        item.put("Auth","Natsai Audrey Chieza");
        item.put("Description","Fashion has a pollution problem — can biology fix it?");
        HashMap<String,String> item2 = new HashMap<>();
        item2.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/c6ade11ac1560a4e074b4ca8786af44265ba2cb3_2880x1620.jpg?quality=89&w=320");
        item2.put("Auth","Deborah Willis and Hank Willis Thomas");
        item2.put("Description","A mother and son united by love and art");
        HashMap<String,String> item3 = new HashMap<>();
        item3.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/165f689aa755f771f585efbd6617032bbf026475_2880x1620.jpg?quality=89&w=320");
        item3.put("Auth","Christian Benimana");
        item3.put("Description","The next generation of African architects and designers");
        HashMap<String,String> item4 = new HashMap<>();
        item4.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/ceef3c7d668ecb207e9001c1b796c37a46ce3276_2880x1620.jpg?quality=89&w=320");
        item4.put("Auth","Xavier De Kestelier");
        item4.put("Description","Adventures of an interplanetary architect");
        HashMap<String,String> item5 = new HashMap<>();
        item5.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/1d79b88bd495d5e8496790e0b000d2588be2c8f0_2880x1620.jpg?quality=89&w=320");
        item5.put("Auth","Gautam Bhan");
        item5.put("Description","A bold plan to house 100 million people");
        HashMap<String,String> item6 = new HashMap<>();
        item6.put("Cover","https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/6483248ecfac9c39eca525fb37663b56589994fe_2880x1620.jpg?quality=89&w=320");
        item6.put("Auth","Martina Flor");
        item6.put("Description","The secret language of letter design");
        data.add(item);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_popular,null);
        View videoView = inflater.inflate(R.layout.featured_video,null);
        JZVideoPlayerStandard jzVideoPlayerStandard = videoView.findViewById(R.id.featured_player);
        jzVideoPlayerStandard.setUp("http://v4.music.126.net/20180110125711/fc3eb21551527a9f577bf0c0fde49a33/web/cloudmusic/ICEwICQwIGAgICAxMGEyMA==/mv/5779389/bfc379a2a065c4a9a83dccf559624b76.mp4",JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
        Picasso.with(getContext()).load("https://pi.tedcdn.com/r/talkstar-photos.s3.amazonaws.com/uploads/69454343-a87d-4e06-9f1c-e23cb4b17282/TouriaElGlaoui_2017G-embed.jpg?quality=63&w=512").into(jzVideoPlayerStandard.thumbImageView);
        ListView listView = view.findViewById(R.id.popular_list);
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
            public View getView(int position, View convertView, ViewGroup parent) {
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
