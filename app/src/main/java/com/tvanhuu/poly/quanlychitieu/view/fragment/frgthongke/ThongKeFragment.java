package com.tvanhuu.poly.quanlychitieu.view.fragment.frgthongke;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private AppCompatTextView txtDay, txtThuDay, txtChiDay, txtMonth, txtThuMonth, txtChiMonth, txtThuTong, txtChiTong, txtYear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStte) {
        return inflater.inflate(R.layout.frg_thongke, container, false);
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
        txtMonth = view.findViewById(R.id.txt_1week);
        txtThuMonth = view.findViewById(R.id.txt_thutuannay);
        txtChiMonth = view.findViewById(R.id.txt_chituannay);
        txtThuTong = view.findViewById(R.id.txt_thuTong);
        txtChiTong = view.findViewById(R.id.txt_chiTong);
        txtYear = view.findViewById(R.id.txt_year);
        pieChi = view.findViewById(R.id.charChi);
        pieThu = view.findViewById(R.id.charThu);
        pieEntriesThu();
        pieEntriesChi();
        setDataView();
    }

    private void pieEntriesChi() {
        pieDataSet = new PieDataSet(pieChiList, "");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(Constant.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieChi.setDrawEntryLabels(false);
        pieChi.setData(pieData);
        pieChi.setDescription(null);
        pieChi.animateY(3000);
        pieChi.invalidate();
    }

    private void pieEntriesThu(){
        pieDataSet = new PieDataSet(pieThuList, "");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(Constant.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieThu.setData(pieData);
        pieThu.setDescription(null);
        pieThu.setDrawEntryLabels(false);
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

    @SuppressLint("SetTextI18n")
    private void setDataView() {
        // card day
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDay = new SimpleDateFormat("d/M/yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleMonth = new SimpleDateFormat("M");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleYear = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String daynow = day+"/"+(month+1)+"/"+year;

        double thuDay = 0;
        double thuMonth = 0;
        double thuYear = 0;
        for (int i = 0; i < datasThu.size(); i++){
            String formatDay = simpleDay.format(datasThu.get(i).getNgayThang());
            String formatMonth = simpleMonth.format(datasThu.get(i).getNgayThang());
            String formatYear = simpleYear.format(datasThu.get(i).getNgayThang());

            if (formatDay.equalsIgnoreCase(daynow)){
                thuDay+=(datasThu.get(i).getSoTien());
            }
            if(formatMonth.equalsIgnoreCase(String.valueOf((month+1)))){
                thuMonth+=(datasThu.get(i).getSoTien());
            }
            if (formatYear.equalsIgnoreCase(String.valueOf(year))){
                thuYear+=(datasThu.get(i).getSoTien());
            }
        }

        double chiDay = 0;
        double chiMonth = 0;
        double chiYear = 0;
        for (int i = 0; i < datasChi.size(); i++){
            String formatDay = simpleDay.format(datasChi.get(i).getNgayThang());
            String formatMonth = simpleMonth.format(datasChi.get(i).getNgayThang());
            String formatYear = simpleYear.format(datasChi.get(i).getNgayThang());
            if (formatDay.equalsIgnoreCase(daynow)){
                chiDay+=(datasChi.get(i).getSoTien());
            }
            if(formatMonth.equalsIgnoreCase(String.valueOf((month+1)))){
                chiMonth+=(datasChi.get(i).getSoTien());
            }
            if (formatYear.equalsIgnoreCase(String.valueOf(year))){
                chiYear+=(datasChi.get(i).getSoTien());
            }
        }


        txtChiDay.setText(String.valueOf(chiDay));
        txtDay.setText("("+daynow+")");
        txtThuDay.setText(String.valueOf(thuDay));

        txtThuMonth.setText(String.valueOf(thuMonth));
        txtChiMonth.setText(String.valueOf(chiMonth));
        txtMonth.setText("("+(month+1)+")");

        txtYear.setText("("+(year)+")");
        txtThuTong.setText(String.valueOf(thuYear));
        txtChiTong.setText(String.valueOf(chiYear));
    }

}
