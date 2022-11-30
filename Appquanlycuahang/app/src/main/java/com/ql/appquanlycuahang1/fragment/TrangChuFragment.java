package com.ql.appquanlycuahang1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ql.appquanlycuahang1.R;
import com.ql.appquanlycuahang1.ThemSanPhamActivity;
import com.ql.appquanlycuahang1.adapter.SanPhamAdapter;
import com.ql.appquanlycuahang1.adapter.TrangChuAdapter;
import com.ql.appquanlycuahang1.datbase.DBSanPham;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    View view;
    List<SanPham> sanPhamList;
    SanPhamAdapter sanPhamAdapter;
    ListView listView;
    Button btnThem;
    DBSanPham dbSanPham;
    RecyclerView recyclerView;
    public static TrangChuFragment newInstance() {

        Bundle args = new Bundle();
        TrangChuFragment fragment = new TrangChuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu,container,false);
        //Ánh xạ
        listView = view.findViewById(R.id.lvSanPham);
//        recyclerView = view.findViewById(R.id.rcv);

        sanPhamList = new ArrayList<>();
        dbSanPham = new DBSanPham(getActivity());
        dbSanPham.open();
        hienThiList();

        btnThem = view.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ThemSanPhamActivity.class));
            }
        });
        return view;
    }
    public void xoaSanPham(String id){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Xóa Sản Phẩm");
        alertDialog.setMessage("Bạn có muốn xóa không ?");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    public void SuaSanPham(String ten, String ncc, String thongtin, int soluong, int id){
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtsuaten = (EditText) dialog.findViewById(R.id.edt_sua_ten);
        EditText edtsuancc = (EditText) dialog.findViewById(R.id.edt_sua_ncc);
        EditText edtsuasoluong = (EditText) dialog.findViewById(R.id.edt_sua_soluong);
        EditText edtsuathongtin = (EditText) dialog.findViewById(R.id.edt_sua_thongtin);
        Button btnSua = (Button) dialog.findViewById(R.id.btn_Sua);
        Button btnHuy = (Button) dialog.findViewById(R.id.btn_Huy);

        edtsuaten.setText(ten);
        edtsuancc.setText(ncc);
        edtsuathongtin.setText(thongtin);
        edtsuasoluong.setText(soluong+"");

        //Sự kiện sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tennew = edtsuaten.getText().toString();
                String NCCnew = edtsuancc.getText().toString();
                String Thongtinnew = edtsuathongtin.getText().toString();
                int Soluongnew = Integer.parseInt(edtsuasoluong.getText().toString());

                if(TextUtils.isEmpty(Tennew) && TextUtils.isEmpty(NCCnew) && TextUtils.isEmpty(Thongtinnew) && TextUtils.isEmpty(Soluongnew + "")){
                    Toast.makeText(getActivity(), "Yêu cầu nhập dữ liệu mới", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                else{
                    dbSanPham.QueryData("Update SanPham SET ten='"+ Tennew +"', nhacungcap='"+ NCCnew +"',thongtin='"+ Thongtinnew +"',soluong='"+ Soluongnew +"' WHERE maSP= '"+ id +"' ");
                    dialog.dismiss();
                    hienThiList();
                }
            }
        });

        //Sự kiện hủy
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void hienThiList(){
        sanPhamList = dbSanPham.GetAll();

        //hien thị bằng list view
        TrangChuAdapter trangChuAdapter = new TrangChuAdapter(getActivity(),sanPhamList,this);
        listView.setAdapter(trangChuAdapter);

        // hiển thị bằng recyclerview
//        SanPhamRecyclerAdapter sanPhamRecyclerAdapter = new SanPhamRecyclerAdapter(getActivity(),sanPhamList,this);
//        //2 Hàng.
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.HORIZONTAL,false);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(sanPhamRecyclerAdapter);
    }
}
