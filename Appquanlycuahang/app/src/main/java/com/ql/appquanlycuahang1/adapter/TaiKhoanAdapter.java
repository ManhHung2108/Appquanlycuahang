package com.ql.appquanlycuahang1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ql.appquanlycuahang1.R;
import com.ql.appquanlycuahang1.model.TaiKhoan;

import java.util.List;

public class TaiKhoanAdapter extends BaseAdapter {

    Context context;
    List<TaiKhoan> taiKhoanList;
    TextView tvTenTK, tvEmail;

    public TaiKhoanAdapter(Context context, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
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
        view = inflater.inflate(R.layout.layout_header_nav, null);
        final TaiKhoan taiKhoan = taiKhoanList.get(i);
        if(taiKhoan != null)
        {
            tvTenTK = view.findViewById(R.id.tvTenNguoiDung);
            tvTenTK.setText(taiKhoanList.get(i).getmTenTaiKhoan());


        }
        return view;
    }
}
