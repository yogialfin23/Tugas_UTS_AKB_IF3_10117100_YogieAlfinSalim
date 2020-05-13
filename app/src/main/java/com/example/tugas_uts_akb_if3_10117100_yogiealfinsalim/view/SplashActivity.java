package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.view;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/** NIM : 10117100
 * Nama : Yogie Alfin Salim
 * Kelas : IF-3
 * Tanggal : 12-05-2020
 **/

public class SplashActivity extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash){

        //menambahkan background
        configSplash.setBackgroundColor(R.color.colorPrimaryDark);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        //menambahkan logo
        configSplash.setLogoSplash(R.drawable.logosplash);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);

        //menambahkan title
        configSplash.setTitleSplash("Connecting People");
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.ZoomIn);
    }
    @Override
    public void animationsFinished(){
        startActivity(new Intent(SplashActivity.this, OnboardSliderActivity.class));
    }
}
