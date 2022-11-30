package com.ql.appquanlycuahang1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ql.appquanlycuahang1.R;
import com.ql.appquanlycuahang1.fragment.TrangChuFragment;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.List;

public class TrangChuAdapter extends BaseAdapter {
    Context context;
    List<SanPham> sanPhamList;
    TextView tvTen,tvSoLuong;
    ImageView imgSanPham,imgXoa,imgSua;
    TrangChuFragment trangChuFragment;



    public TrangChuAdapter(Context context, List<SanPham> sanPhamList, TrangChuFragment trangChuFragment) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.trangChuFragment = trangChuFragment;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_sanpham,null);
        final SanPham sanPham = sanPhamList.get(i);
        if (sanPham!=null){
            tvTen = view.findViewById(R.id.tvTen);
            tvTen.setText(sanPhamList.get(i).ten);

            tvSoLuong = view.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText(sanPhamList.get(i).soLuong+"");

            //Bắt sự kiện sửa
            imgXoa = view.findViewById(R.id.imgXoa);
            imgXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trangChuFragment.xoaSanPham(String.valueOf(sanPham.maSP));
                }
            });

            //Bắt sự kiện cho img Sửa
            imgSua = view.findViewById(R.id.imgSua);
            imgSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trangChuFragment.SuaSanPham(sanPham.ten, sanPham.nhacungcap, sanPham.thongtin, sanPham.soLuong, sanPham.maSP);
                }
            });
        }

        return view;
    }
}
