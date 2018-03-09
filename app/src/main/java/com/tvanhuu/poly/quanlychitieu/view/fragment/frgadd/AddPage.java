package com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.view.adapter.CustomAdapterPageView;

/**
 * Created by thuu on 09/03/18.
 **/

public class AddPage extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_page_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager.setAdapter(new CustomAdapterPageView(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);

    }
}
