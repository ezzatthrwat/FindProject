package com.example.zizoj.findproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zizoj.findproject.Tabs.EmbeddedTab;
import com.example.zizoj.findproject.Tabs.ItTab;
import com.example.zizoj.findproject.Tabs.MobileTab;
import com.example.zizoj.findproject.Tabs.WebTab;

/**
 * Created by zizoj on 11/04/2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    ItTab itTab;
    EmbeddedTab embeddedTab;
    MobileTab mobileTab ;
    WebTab webTab ;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                itTab = new ItTab();
                return itTab;
            case 1:
                mobileTab = new MobileTab();
                return mobileTab;
            case 2:
                webTab = new WebTab();
                return webTab;
            case 3 :
                embeddedTab = new EmbeddedTab();
                return embeddedTab;
            default:
                return null;
        }
    }

    public ItTab getItTab(){
        return itTab;
    }
    public EmbeddedTab getEmbeddedTab(){
        return embeddedTab;
    }
    public MobileTab getMobileTab(){
        return mobileTab;
    }
    public WebTab getWebTab (){
        return webTab;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "desktop";
            case 1:
                return "Mobile";
            case 2:
                return "Web";
            case 3 :
                return "Embedded";
        }
        return null;
    }
}