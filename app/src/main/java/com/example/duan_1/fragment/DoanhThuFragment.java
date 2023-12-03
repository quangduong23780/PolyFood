package com.example.duan_1.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_1.R;
import com.example.duan_1.dao.TopDAO;

import java.util.Calendar;

public class DoanhThuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu,container,false);

        EditText edtStart = view.findViewById(R.id.edtStart);
        EditText edtEnd = view.findViewById(R.id.edtEnd);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtKetQua = view.findViewById(R.id.txtKetQua);

        Calendar calendar = Calendar.getInstance();
        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String ngay ="";
                                String thang ="";
                                if (i2 < 10){
                                    ngay ="0"+i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }
                                if (i1 + 1 <10){
                                    thang = "0"+ (i1+1);
                                }else {
                                    thang = String.valueOf(i1 +1);
                                }
                               edtStart.setText(ngay+ "/"+ thang + "/" + i);
                            }
                        },
                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String ngay ="";
                                String thang ="";
                                if (i2 < 10){
                                    ngay ="0"+i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }
                                if (i1 + 1 <10){
                                    thang = "0"+ (i1+1);
                                }else {
                                    thang = String.valueOf(i1 +1);
                                }
                                edtEnd.setText(ngay+ "/"+ thang + "/" + i);
                            }
                        },
                        calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH),
                        calendar.get(calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopDAO topDAO = new TopDAO(getContext());
                String ngaybatdau = edtStart.getText().toString();
                String ngayketthuc = edtEnd.getText().toString();
                int doanhthu = topDAO.getDoanhThu(ngaybatdau,ngayketthuc);
                txtKetQua.setText(doanhthu+"VNĐ");
            }
        });
        return view;
    }
}
