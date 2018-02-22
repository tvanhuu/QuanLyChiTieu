package com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tvanhuu.poly.quanlychitieu.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thuu on 28/01/18.
 **/

public class AddFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout relativeLayout;
    private AppCompatEditText edtSoTien;
    private AppCompatButton btnGhi;
    private AppCompatTextView txtHinhAnh, txtMuc, txtGhiChu, txtLoai, txtNgayThang;
    private AppCompatImageView imgMucChi, imgGhiChu, imgLoai, imNgayThang, imgAnh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStte) {
        return inflater.inflate(R.layout.frg_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewMain(view);
        setOnattach();
    }

    private void setViewMain(View view) {
        edtSoTien = view.findViewById(R.id.edt_soTien);
        btnGhi = view.findViewById(R.id.btn_ghi);
        txtHinhAnh = view.findViewById(R.id.txt_hinhAnh);
        txtMuc = view.findViewById(R.id.txt_mucChi);
        txtGhiChu = view.findViewById(R.id.txt_ghiChu);
        txtLoai = view.findViewById(R.id.txt_loai);
        txtNgayThang = view.findViewById(R.id.txt_ngayThang);
        imgMucChi = view.findViewById(R.id.img_mucChi);
        imgGhiChu = view.findViewById(R.id.img_ghiChu);
        imgLoai = view.findViewById(R.id.img_loai);
        imNgayThang = view.findViewById(R.id.img_ngayThang);
        imgAnh = view.findViewById(R.id.img_anh);
        relativeLayout = view.findViewById(R.id.RelativeLayout);
    }

    private void setOnattach() {
        imgMucChi.setOnClickListener(this);
        imgGhiChu.setOnClickListener(this);
        imgLoai.setOnClickListener(this);
        imNgayThang.setOnClickListener(this);
        btnGhi.setOnClickListener(this);
        txtHinhAnh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_mucChi:
                if(txtLoai.getText().toString().equalsIgnoreCase("")){
                    Snackbar.make(relativeLayout , "Hãy chọn loại trước", 4000)
                            .setActionTextColor(Color.RED)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getContext(), "Đùa thôi điền thông tin đi :))", Toast.LENGTH_LONG).show();
                                }
                            })
                            .show();
                }else if(txtLoai.getText().toString().equalsIgnoreCase("Thu")){
                    selectMucThu();
                }else{
                    selectMucChi();
                }
                break;
            case R.id.img_ghiChu:
                typingGhiChu();
                break;
            case R.id.img_loai:
                selectLoai();
                break;
            case R.id.img_ngayThang:
                selectDate();
                break;
            case R.id.txt_hinhAnh:
                selectImage();
                break;
            case R.id.btn_ghi:
                Toast.makeText(getContext(), "Comming Soon", Toast.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(relativeLayout , "Hãy nhập đầy đủ", 2000)
                        .setActionTextColor(Color.RED)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), "Đùa thôi điền thông tin đi :))", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;

        }
    }

    private void selectDate(){
        Calendar ca = Calendar.getInstance();
        DatePickerDialog dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                txtNgayThang.setText(d + "/" +(m+1)+ "/" + y);
            }
        }, ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));
        dp.show();
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
    }

    private void selectLoai(){
        final String[] listLoai = getResources().getStringArray(R.array.chonloai);
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        ab.setTitle("Chọn loại");
        ab.setCancelable(false);
        ab.setSingleChoiceItems(listLoai, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtLoai.setText(listLoai[i]);
                dialogInterface.dismiss();
            }
        });
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ad = ab.create();
        ad.show();
    }

    private void typingGhiChu(){
        final AlertDialog ab = new AlertDialog.Builder(getActivity()).create();
        @SuppressLint("InflateParams") View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_note, null);
        ab.setView(v);
        ab.setCancelable(false);
        final TextInputEditText edtGhiChu = v.findViewById(R.id.txt_ghichudialog);
        edtGhiChu.setText(txtGhiChu.getText().toString());
        v.findViewById(R.id.btn_huy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ab.dismiss();
            }
        });
        v.findViewById(R.id.btn_xong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtGhiChu.setText(edtGhiChu.getText().toString());
                ab.dismiss();
            }
        });

        ab.show();
    }

    private void selectMucChi() {
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        final String[] mucChi = new String[]{
                "Cafe",
                "Tiền điện",
                "Tiền nước",
                "Tiền thẻ điện thoại",
                "Tiền ăn sáng",
                "Tiền mua bao cao su",
                "Tiền mua băng vệ sinh",
                "Tiền mua quần áo",
                "Tiền đổ xăng",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su",
                "Tiền mua bao cao su"
        };

        final boolean[] checkedMucChi = new boolean[]{
                false, false, false, false, false , false , false , false,false,
                false, false, false, false, false , false , false , false, false,
                false, false, false, false, false , false , false , false, false,
                false, false, false, false, false , false , false , false, false,
                false, false, false, false, false , false , false , false, false
        };

        final List<String> datas = Arrays.asList(mucChi);
        ab.setMultiChoiceItems(mucChi, checkedMucChi, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checkedMucChi[i] = b;
            }
        });
        ab.setCancelable(false);
        ab.setTitle("Hãy chọn khoản chi");
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int x = 0; x < checkedMucChi.length; x++){
                    boolean check = checkedMucChi[x];
                    if(check){
                        txtMuc.setText(datas.get(x));
                        break;
                    }
                }
            }
        });

        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog ad =ab.create();
        ad.show();
    }

    private void selectMucThu(){
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        String[] arrThu = {
                "Tiền Lương",
                "Tiền Thưởng",
                "Được cho/tặng",
                "Tiền lãi",
                "Lãi tiết kiệm",
                "Khác",
        };
        final boolean[] checkThu = {false, false, false, false, false, false};
        final List<String> data = Arrays.asList(arrThu);
        ab.setMultiChoiceItems(arrThu, checkThu, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checkThu[i] = b;
            }
        });
        ab.setTitle("Hãy chọn khoản thu");
        ab.setCancelable(false);
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int x = 0; x < checkThu.length; x++){
                    boolean check = checkThu[x];
                    if (check){
                        txtMuc.setText(data.get(x));
                        break;
                    }
                }
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog ad = ab.create();
        ad.show();
    }

    private void addData(){
        //http://www.coderzheaven.com/2012/12/23/store-image-android-sqlite-retrieve-it/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && imageReturnedIntent != null) {
            Uri imageUri = imageReturnedIntent.getData();
            imgAnh.setImageURI(imageUri);
        }
    }
}