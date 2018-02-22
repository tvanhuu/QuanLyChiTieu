package com.tvanhuu.poly.quanlychitieu.view.fragment.frgthu;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.model.Loai;
import com.tvanhuu.poly.quanlychitieu.view.activity.MainActivity;
import com.tvanhuu.poly.quanlychitieu.view.adapter.AdapterItemView;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd.AddFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thuu on 28/01/18.
 **/

public class KhoanThuFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private AdapterItemView adapterItemView;
    private List<Loai> datas;
    private CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStte) {
        return inflater.inflate(R.layout.frg_khoanthu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        setOnAttach();
    }

    private void initData() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "07/07/2017";
        String dateInString1 = "07/08/2017";
        String dateInString2 = "07/08/2017";
        String dateInString3 = "07/09/2017";
        String dateInString4 = "07/10/2017";
        String dateInString5 = "07/11/2017";
        String dateInString6 = "07/12/2017";
        try {
            Date date = simpleDateFormat.parse(dateInString);
            Date date1 = simpleDateFormat.parse(dateInString1);
            Date date2 = simpleDateFormat.parse(dateInString2);
            Date date3 = simpleDateFormat.parse(dateInString3);
            Date date4 = simpleDateFormat.parse(dateInString4);
            Date date5 = simpleDateFormat.parse(dateInString5);
            Date date6 = simpleDateFormat.parse(dateInString6);
            datas = new ArrayList<>();
            datas.add(new Loai("Lương", "Lương tháng 7","Thu", R.drawable.logo_user,30, date ));
            datas.add(new Loai("Tiền thưởng", "Thưởng tăng ca tháng 7","Thu", R.drawable.logo_user,30, date ));
            datas.add(new Loai("Lương", "Lương tháng 8","Thu", R.drawable.logo_user,30, date1 ));
            datas.add(new Loai("Tiền thưởng", "Thưởng tăng ca tháng 8","Thu", R.drawable.logo_user,30, date1 ));
            datas.add(new Loai("Lương", "Lương tháng 9","Thu", R.drawable.logo_user,30, date2 ));
            datas.add(new Loai("Tiền thưởng", "Thưởng tăng ca tháng 9","Thu", R.drawable.logo_user,30, date2 ));
            datas.add(new Loai("Lương", "Lương tháng 10","Thu", R.drawable.logo_user,30, date3 ));
            datas.add(new Loai("Lương", "Lương tháng 11","Thu", R.drawable.logo_user,30, date4 ));
            datas.add(new Loai("Lương", "Lương tháng 12","Thu", R.drawable.logo_user,30, date5 ));
            datas.add(new Loai("Lương", "Lương tháng 1","Thu", R.drawable.logo_user,30, date6 ));
            datas.add(new Loai("Tiền thưởng", "Thưởng tăng ca tháng 12","Thu", R.drawable.logo_user,30, date6 ));


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void initView(View view) {
        coordinatorLayout = view.findViewById(R.id.CoordinatorLayout);
        fab = view.findViewById(R.id.fab_thu);
        recyclerView = view.findViewById(R.id.rcv_khoanthu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterItemView = new AdapterItemView(datas);
        recyclerView.setAdapter(adapterItemView);
        initSwipe();
    }

    private void setOnAttach() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).nextFragment(new AddFragment());
            }
        });
    }

    private void initSwipe() {
        final Paint p = new Paint();
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    final Loai khoanChi = new Loai(datas.get(position).getTen(),
                            datas.get(position).getGhiChu(),
                            datas.get(position).getLoai(),
                            datas.get(position).getImages(),
                            datas.get(position).getSoTien(),
                            datas.get(position).getNgayThang());
                    Snackbar.make(coordinatorLayout, "You are Delete!", 3000)
                            .setActionTextColor(Color.RED)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    adapterItemView.addItem(position, khoanChi);
                                }
                            }).show();
                    adapterItemView.removeItem(position);
                } else {
                    // edit object
//                    removeView();
//                    edit_position = position;
                    adapterItemView.updateList(datas);
                    Toast.makeText(getContext(), "Comming Soon", Toast.LENGTH_LONG).show();
                }
            }
//            private void removeView(){
//                if(view.getParent()!=null) {
//                    ((ViewGroup) view.getParent()).removeView(view);
//                }
//            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
