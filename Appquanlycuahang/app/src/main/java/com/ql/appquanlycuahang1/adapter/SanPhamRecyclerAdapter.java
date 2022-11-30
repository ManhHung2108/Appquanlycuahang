package com.ql.appquanlycuahang1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.ql.appquanlycuahang1.R;
import com.ql.appquanlycuahang1.fragment.TrangChuFragment;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.List;

public class SanPhamRecyclerAdapter extends RecyclerView.Adapter<SanPhamRecyclerAdapter.ViewHolder> {
    Context context;
    List<SanPham> sanPhamList;
    TrangChuFragment trangChuFragment;

    public SanPhamRecyclerAdapter(Context context, List<SanPham> sanPhamList, TrangChuFragment trangChuFragment) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.trangChuFragment = trangChuFragment;
    }

    //Chuyển xml thành view
    public SanPhamRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sanphamrcv,parent,false);
        return new ViewHolder(view);
    }

    //Đăng ký sự kiện
    @Override
    public void onBindViewHolder( SanPhamRecyclerAdapter.ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.tvTen.setText(sanPham.ten);
        holder.tvSoLuong.setText(sanPham.soLuong+"");

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trangChuFragment.xoaSanPham(String.valueOf(sanPham.maSP));
            }
        });
        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trangChuFragment.SuaSanPham(sanPham.ten, sanPham.nhacungcap, sanPham.thongtin, sanPham.soLuong, sanPham.maSP);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen,tvSoLuong;
        ImageView imgSanPham,imgXoa,imgSua;
        public ViewHolder( View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            imgXoa = itemView.findViewById(R.id.imgXoa);
            imgSua = itemView.findViewById(R.id.imgSua);

        }
    }
}
