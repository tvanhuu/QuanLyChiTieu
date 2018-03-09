package com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.common.Constant;
import com.tvanhuu.poly.quanlychitieu.dao.SQLManager;
import com.tvanhuu.poly.quanlychitieu.model.ObjectKhoan;
import com.tvanhuu.poly.quanlychitieu.view.adapter.AdapterItemKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuu on 09/03/18.
 **/

public class AddKhoan extends Fragment {

    private RecyclerView rvKhoan;
    private EditText edtName;
    private Button btnAddKhoan;
    private AdapterItemKhoan adapterItemKhoan;
    private List<ObjectKhoan> datas;
    private SQLManager db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_add_khoan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        onAttach();
    }

    private void initData() {
        db = new SQLManager(getContext());
        datas = new ArrayList<>();
        if (Constant.status == 1) {
            datas = db.getKhoanChi();
        } else {
            datas = db.getKhoanThu();
        }
    }

    private void initView(View view) {
        rvKhoan = view.findViewById(R.id.rv_khoan);
        edtName = view.findViewById(R.id.edt_name);
        btnAddKhoan = view.findViewById(R.id.btn_add_khoan);

        rvKhoan.setHasFixedSize(true);
        rvKhoan.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterItemKhoan = new AdapterItemKhoan(datas, getContext());
        rvKhoan.setAdapter(adapterItemKhoan);
    }

    private void onAttach() {
        btnAddKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.status == 1) {
                    db.addKhoan(new ObjectKhoan(edtName.getText().toString(), "Chi"));
                    datas.add(new ObjectKhoan(edtName.getText().toString(), "Chi"));
                    adapterItemKhoan.updateList(datas);
                } else {
                    db.addKhoan(new ObjectKhoan(edtName.getText().toString(), "Thu"));
                    datas.add(new ObjectKhoan(edtName.getText().toString(), "Thu"));
                    adapterItemKhoan.updateList(datas);
                }
            }
        });
    }
}
