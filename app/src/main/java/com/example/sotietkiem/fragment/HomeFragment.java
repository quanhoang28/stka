package com.example.sotietkiem.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sotietkiem.R;
import com.google.gson.Gson;

import java.util.zip.Inflater;

import data.User;
import data.Utils;


public class HomeFragment extends Fragment {

    private final Gson gson = new Gson();
    EditText edSoTien;
    Button btnXacNhan,btnDangNhap;
    TextView tvKetQua,tvTen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_home, container, false);

        edSoTien = v.findViewById(R.id.edSotien);
        btnXacNhan = v.findViewById(R.id.btnXacNhan);
        tvKetQua = v.findViewById(R.id.tvKetQua);
        tvTen = v.findViewById(R.id.tvTen );


        SharedPreferences shareget = getActivity().getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        String userPref = shareget.getString(Utils.KEY_USER,null);

        User user = gson.fromJson(userPref,User.class);

        if (user == null) {
            tvTen.setText("Tên tài khoản");

        }
        else {
            String info = user.getUserName();
            tvTen.setText(info);
        }

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cong();

                edSoTien.setText(String.valueOf(("")));
            }
        });
        return v;
    }

    void ShowData(int rs){
        tvKetQua.setText(String.valueOf(rs));
    }

    void Cong(){
        String txtSoTienCong = edSoTien.getText().toString();
        String txtSoTienHienTai = tvKetQua.getText().toString();
        if (txtSoTienCong.isEmpty()){
            tvKetQua.setText("Số tiền hiện có");
            return;
        }
        int a = Integer.valueOf(txtSoTienHienTai);
        int b = Integer.valueOf(txtSoTienCong);

        int c = a+b;

        ShowData(c);
    }

}