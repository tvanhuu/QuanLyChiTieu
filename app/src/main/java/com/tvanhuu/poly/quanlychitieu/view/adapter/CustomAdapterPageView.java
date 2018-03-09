package com.tvanhuu.poly.quanlychitieu.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd.AddKhoan;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd.AddLoai;

/**
 * Created by thuu on 09/03/18.
 **/

public class CustomAdapterPageView extends FragmentPagerAdapter{
    private Context context;
    private int NUMBER_ITEM = 2;
    private String[] titles = new String[]{"Khoản", "Loại"};

    public CustomAdapterPageView(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AddLoai();
            case 1:
                return new AddKhoan();
            default:
                return new AddLoai();

        }
    }

    @Override
    public int getCount() {
        return NUMBER_ITEM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }
}
