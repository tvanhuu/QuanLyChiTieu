package com.tvanhuu.poly.quanlychitieu.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.dao.SQLManager;
import com.tvanhuu.poly.quanlychitieu.model.ObjectKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuu on 09/03/18.
 **/

public class AdapterItemKhoan extends RecyclerView.Adapter<AdapterItemKhoan.RecycleViewHolder> {

    private List<ObjectKhoan> datas = new ArrayList<>();
    private Context mcontext;
    private SQLManager db;

    public AdapterItemKhoan(List<ObjectKhoan> datas, Context context) {
        this.datas = datas;
        this.mcontext = context;
        db = new SQLManager(mcontext);
    }

    public void updateList(List<ObjectKhoan> datasList) {
        datas = datasList;
        notifyDataSetChanged();
    }

    public void addItem(int position, ObjectKhoan khoanKhoan) {
        datas.add(position, khoanKhoan);
        notifyItemInserted(position);
    }

    public void editItem(int position, ObjectKhoan khoanKhoan) {
        datas.set(position, khoanKhoan);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size());
    }

    @Override
    public AdapterItemKhoan.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_khoan, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdapterItemKhoan.RecycleViewHolder holder, final int position) {
        holder.txtName.setText(datas.get(position).getName());

        // img edit
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mcontext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dia_edit_loai);

                final EditText edtName = dialog.findViewById(R.id.txt_loaidialog);
                Button btnYes = dialog.findViewById(R.id.btn_edit);
                Button btnNo = dialog.findViewById(R.id.btn_cancel);

                edtName.setText(datas.get(holder.getAdapterPosition()).getName());
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.updateKhoan(new ObjectKhoan( datas.get(holder.getAdapterPosition()).getId(), edtName.getText().toString(), datas.get(holder.getAdapterPosition()).getType()));
                        editItem(holder.getAdapterPosition(), new ObjectKhoan( datas.get(holder.getAdapterPosition()).getId(), edtName.getText().toString(), datas.get(holder.getAdapterPosition()).getType()));
                        Toast.makeText(mcontext, "Đã sửa ^^!", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        // img delete
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
                dialog.setMessage("Bạn có chắc muốn xóa bản ghi?");
                dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteKhoan(new ObjectKhoan( datas.get(holder.getAdapterPosition()).getId(), datas.get(holder.getAdapterPosition()).getName(), datas.get(holder.getAdapterPosition()).getType()));
                        removeItem(holder.getAdapterPosition());
                        Toast.makeText(mcontext, "Đã xóa ^^!", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class RecycleViewHolder extends RecyclerView.ViewHolder {

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
