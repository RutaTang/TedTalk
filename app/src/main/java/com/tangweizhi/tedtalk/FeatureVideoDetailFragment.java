package com.tangweizhi.tedtalk;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by tangweizhi on 2018/1/10.
 */

public class FeatureVideoDetailFragment extends Fragment {
    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature_video_detail,null);
        TextView backIcon = view.findViewById(R.id.back);
        final TextView detailAuth = view.findViewById(R.id.detailAuth);
        final TextView detailDescription = view.findViewById(R.id.detailDescription);
        backIcon.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"iconfont/iconfont.ttf"));
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.leftout_to_rightin,R.anim.leftin_to_rightout);
                fragmentTransaction.replace(R.id.fragmentContainer,new FeatureFragment());
                fragmentTransaction.commit();
            }
        });
        final JZVideoPlayerStandard jzVideoPlayerStandard = view.findViewById(R.id.detailVideo);
        jzVideoPlayerStandard.setUp("http://v4.music.126.net/20180110211121/af420542c33075ef5b0452ada1fd86d8/web/cloudmusic/ICEwICQwIGAgICAxMGEyMA==/mv/5779389/bfc379a2a065c4a9a83dccf559624b76.mp4",JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
        jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jzVideoPlayerStandard.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailAuth.setAlpha(0);
                detailDescription.setAlpha(0);
                jzVideoPlayerStandard.startVideo();
            }
        });
        Picasso.with(getContext()).load("https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/7e282e3b442c167b2993f0ef4a51d5e641174c1d_2880x1620.jpg?quality=63&w=512").into(jzVideoPlayerStandard.thumbImageView);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
