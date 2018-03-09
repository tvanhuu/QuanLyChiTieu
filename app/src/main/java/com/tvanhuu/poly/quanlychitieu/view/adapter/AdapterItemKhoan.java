package com.tvanhuu.poly.quanlychitieu.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.model.ObjectKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuu on 09/03/18.
 **/

public class AdapterItemKhoan extends RecyclerView.Adapter<AdapterItemKhoan.RecycleViewHolder>{

    private List<ObjectKhoan> datas = new ArrayList<>();
    private Context mcontext;

    public AdapterItemKhoan(List<ObjectKhoan> datas,Context context) {
        this.datas = datas;
        this.mcontext = context;
    }

    public void updateList(List<ObjectKhoan> datasList){
        datas = datasList;
        notifyDataSetChanged();
    }

    public void addItem(int position, ObjectKhoan khoanKhoan){
        datas.add(position, khoanKhoan);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size());
    }

    @Override
    public AdapterItemKhoan.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_khoan, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdapterItemKhoan.RecycleViewHolder holder, int position) {
        holder.txtName.setText(datas.get(position).getName());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, "Edit", Toast.LENGTH_LONG).show();
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(holder.getAdapterPosition());
                Toast.makeText(mcontext, "Delete", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class RecycleViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgEdit, imgDelete;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name_khoan);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);

        }
    }


}
