package com.ql.appquanlycuahang1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ql.appquanlycuahang1.fragment.ShopFragment;
import com.ql.appquanlycuahang1.fragment.ThongTinFrangment;
import com.ql.appquanlycuahang1.fragment.TrangChuFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar  toolbar;
    View headerView;
    TextView tvTen;
    ImageView imgTimKiem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        //Nhận dữ liệu ở màn hình đăng nhập gửi tới
        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        String tentk = i.getStringExtra("tentaikhoan");
        String emailtk = i.getStringExtra("email");


        //Bật ra ShopFragment trước màn hình trang chính
        FragmentManager manager = getSupportFragmentManager();
        ShopFragment shopFragment = new ShopFragment();
        manager.beginTransaction()
                .replace(R.id.content_frame,shopFragment)
                .commit();


        NavigationView navigationView = findViewById(R.id.navigation_view);
        //headerView
        headerView = navigationView.getHeaderView(0);
        tvTen = headerView.findViewById(R.id.tvTenNguoiDung);
        tvTen.setText("Welcome "+ tentk );
        //Custom ToolBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawerlayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Bắt sự kiện khi click vào item của NavigationView

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager =getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_shop:
                        ShopFragment shopFragment1 = new ShopFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,shopFragment1)
                                .commit();
                        break;
                    case R.id.nav_trangchu:
                        TrangChuFragment trangChuFragment = new TrangChuFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,trangChuFragment)
                                .commit();
                        break;
                    case R.id.nav_thongtin:
                        ThongTinFrangment thongTinFrangment = new ThongTinFrangment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,thongTinFrangment)
                                .commit();
                        break;
                    case R.id.nav_dangxuat:
                        startActivity(new Intent(getApplicationContext(),DangNhap.class));
                        finish();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        imgTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManTimKiem.class);
                startActivity(intent);
            }
        });
  }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerlayout);
        imgTimKiem = findViewById(R.id.img_timkiem);
    }

    //Xử lý nút back device
    private long backPressedTime;
    @Override
    public void onBackPressed()
    {
        //Nếu drawer đang mở thì đóng lại khi click vào back của device
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            if(backPressedTime + 2000 > System.currentTimeMillis()) {
                //Nếu đóng rồi thoát app
                super.onBackPressed();
            }
            else{
                Toast.makeText(MainActivity.this, "Ấn lần nữa để thoát ứng dụng.",Toast.LENGTH_LONG).show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }
}