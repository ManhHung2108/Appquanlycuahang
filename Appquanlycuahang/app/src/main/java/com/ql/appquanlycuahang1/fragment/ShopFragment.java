package com.ql.appquanlycuahang1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ql.appquanlycuahang1.ManNoiDung;
import com.ql.appquanlycuahang1.R;
import com.ql.appquanlycuahang1.adapter.SanPhamAdapter;
import com.ql.appquanlycuahang1.datbase.DBSanPham;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    View view;
    List<SanPham> sanPhamList;
    DBSanPham dbSanPham;
    ListView listView;
    SanPhamAdapter sanPhamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop,container,false);
        listView = view.findViewById(R.id.lvShop);

        sanPhamList = new ArrayList<>();
        dbSanPham = new DBSanPham(getActivity());

        //Cho phép mở, đọc, và sửa
        dbSanPham.open();

        // lấy toàn bộ dl từ csdl ra
        hienThiList();

        //Hiển thị thông tin sản phẩm
        chiTietSanPham();

        return view;

    }

    private void chiTietSanPham() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sanPham = sanPhamList.get(i);
                Intent intent = new Intent(getActivity(), ManNoiDung.class);
                intent.putExtra("ten",sanPham.ten);
                intent.putExtra("nhacungcap",sanPham.nhacungcap);
                intent.putExtra("thongtin",sanPham.thongtin);
                intent.putExtra("soluong",sanPham.soLuong);
                startActivity(intent);
            }
        });
    }

    public void xoaSanPham(String id){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Xóa Sản Phẩm");
        alertDialog.setMessage("Bạn có muốn xóa không ?");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SanPham sanPham = new SanPham();

                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                dbSanPham.QueryData("DELETE FROM SanPham WHERE maSP = '"+id+"'");
                hienThiList();
                dialogInterface.cancel();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog.show();
    }
    public void hienThiList(){
        sanPhamList = dbSanPham.GetAll();
        sanPhamAdapter = new SanPhamAdapter(getActivity(),sanPhamList,this);
        listView.setAdapter(sanPhamAdapter);

    }
}
