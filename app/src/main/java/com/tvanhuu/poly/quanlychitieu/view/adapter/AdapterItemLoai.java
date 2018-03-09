package com.tvanhuu.poly.quanlychitieu.view.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.model.ObjectLoai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuu on 18/02/18.
 **/

public class AdapterItemLoai extends RecyclerView.Adapter<AdapterItemLoai.RecyclerViewHolder>{

    private List<ObjectLoai> datas = new ArrayList<>();

    public AdapterItemLoai(List<ObjectLoai> datas) {
        this.datas = datas;
    }

    public void updateList(List<ObjectLoai> datasList){
        datas = datasList;
        notifyDataSetChanged();
    }

    public void addItem(int position, ObjectLoai khoanThu){
        datas.add(position, khoanThu);
        notifyItemInserted(position);
    }
    public void removeItem(int position){
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_loai, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtTen.setText(datas.get(position).getTen());
        holder.txtLoai.setText(datas.get(position).getLoai());
        holder.txtGhiChu.setText(datas.get(position).getGhiChu());
        holder.txtSoTien.setText(String.valueOf(datas.get(position).getSoTien()));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = formatter.format(datas.get(position).getNgayThang());
        holder.txtNgayThang.setText(dateFormat);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTen, txtLoai, txtGhiChu, txtSoTien, txtNgayThang;
        public RecyclerViewHolder(View View) {
            super(View);
            txtTen = View.findViewById(R.id.txt_ten);
            txtLoai = View.findViewById(R.id.txt_loai);
            txtGhiChu = View.findViewById(R.id.txt_ghichu);
            txtSoTien = View.findViewById(R.id.txt_sotien);
            txtNgayThang = View.findViewById(R.id.txt_ngaythang);
        }
    }
}
