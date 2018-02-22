package com.tvanhuu.poly.quanlychitieu.view.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tvanhuu.poly.quanlychitieu.R;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgadd.AddFragment;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgthongke.ThongKeFragment;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frggioithieu.GioiThieuFragment;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgchi.KhoanChiFragment;
import com.tvanhuu.poly.quanlychitieu.view.fragment.frgthu.KhoanThuFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nNavigation;
    private View nHeader;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewMain();
        onAttach();
    }

    private void setViewMain() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        nNavigation =  findViewById(R.id.nvView);
        nHeader = nNavigation.inflateHeaderView(R.layout.nav_header);
        setSupportActionBar(toolbar);

        drawerToggle = setupDrawerToggle();
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        setupDrawerContent(nNavigation);
        nextFragment(new ThongKeFragment());
    }

    private void onAttach() {
        nHeader.findViewById(R.id.img_BackNavigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_thu:
                                nextFragment(new KhoanThuFragment());
                                toolbar.setTitle("Khoản Thu");
                                break;
                            case R.id.nav_chi:
                                nextFragment(new KhoanChiFragment());
                                toolbar.setTitle("Khoản Chi");
                                break;
                            case R.id.nav_thongke:
                                nextFragment(new ThongKeFragment());
                                toolbar.setTitle("Thống Kê");
                                break;
                            case R.id.nav_gioithieu:
                                nextFragment(new GioiThieuFragment());
                                toolbar.setTitle("Giới Thiệu");
                                break;
                            case R.id.nav_thoat:
                               exitDialog();
                                break;
                            default:
                                nextFragment(new ThongKeFragment());
                        }
                        menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    public void nextFragment(Fragment fragment){
        @SuppressLint("CommitTransaction") FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.myLayout , fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.setCustomAnimations(R.anim.fade_in , R.anim.fade_out);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        try {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.myLayout);
            if (fragment instanceof ThongKeFragment) {
                exitDialog();
            } else {
                super.onBackPressed();
            }
        } catch (Exception ex) {
            System.exit(0);
            Log.e("Erro Activity" , String.valueOf(ex));
        }
    }

    private void exitDialog(){
        final AlertDialog.Builder mExit = new AlertDialog.Builder(MainActivity.this);
        mExit.setTitle("Xác nhận");
        mExit.setMessage("Bạn chắc muốn thoát?");
        // Yes
        mExit.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        // No
        mExit.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = mExit.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogTheme;
        dialog.show();
    }
}
