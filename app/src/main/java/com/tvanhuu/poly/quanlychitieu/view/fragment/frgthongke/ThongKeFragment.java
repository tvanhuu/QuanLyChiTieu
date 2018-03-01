package com.tvanhuu.poly.quanlychitieu.view.fragment.frgthongke;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.common.Constant;
import com.tvanhuu.poly.quanlychitieu.dao.SQLManager;
import com.tvanhuu.poly.quanlychitieu.model.ThuChi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuu on 28/01/18.
 **/

public class ThongKeFragment extends Fragment {

    private PieChart pieThu;
    private PieChart pieChi;
    private ArrayList<PieEntry> pieChiList;
    private ArrayList<PieEntry> pieThuList;
    private List<ThuChi> datasThu;
    private List<ThuChi> datasChi;
    private PieDataSet pieDataSet ;
    private PieData pieData ;
    private SQLManager db;
    private AppCompatTextView txtDay, txtThuDay, txtChiDay, txtWeek, txtThuWeek, txtChiWeek, txtThuTong, txtChiTong, txtYear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStte) {
        return inflater.inflate(R.layout.frge_thongke, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
    }

    private void initData() {
        db = new SQLManager(getContext());
        pieChiList = new ArrayList<>();
        pieThuList = new ArrayList<>();
        datasThu = new ArrayList<>();
        datasChi = new ArrayList<>();
        datasThu = db.getThu();
        datasChi = db.getChi();
        AddValuesEntriesThu();
        AddValuesEntriesChi();
    }

    private void initView(View view) {
        txtDay = view.findViewById(R.id.txt_1day);
        txtThuDay = view.findViewById(R.id.txt_thuhomnay);
        txtChiDay = view.findViewById(R.id.txt_chihomnay);
        txtWeek = view.findViewById(R.id.txt_1week);
        txtThuWeek = view.findViewById(R.id.txt_thutuannay);
        txtChiWeek = view.findViewById(R.id.txt_chituannay);
        txtThuTong = view.findViewById(R.id.txt_thuTong);
        txtChiTong = view.findViewById(R.id.txt_chiTong);
        txtYear = view.findViewById(R.id.txt_year);
        pieChi = view.findViewById(R.id.charChi);
        pieThu = view.findViewById(R.id.charThu);
        pieEntriesThu();
        pieEntriesChi();
    }

    private void pieEntriesChi() {
        pieDataSet = new PieDataSet(pieChiList, "");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(Constant.COLORFUL_COLORS);
        pieChi.setData(pieData);
        pieChi.animateY(3000);
        pieChi.invalidate();
    }

    private void pieEntriesThu(){
        pieDataSet = new PieDataSet(pieThuList, "");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(Constant.COLORFUL_COLORS);
        pieThu.setData(pieData);
        pieThu.animateY(3000);
        pieThu.invalidate();
    }

    private void AddValuesEntriesThu() {
        for(int i = 0; i < datasThu.size(); i++){
            pieThuList.add(new PieEntry((float) (datasThu.get(i).getSoTien()), datasThu.get(i).getTen()));
        }
    }

    private void AddValuesEntriesChi() {
        for(int i = 0; i < datasChi.size(); i++){
            pieChiList.add(new PieEntry((float) (datasChi.get(i).getSoTien()), datasChi.get(i).getTen()));
        }
    }

}
