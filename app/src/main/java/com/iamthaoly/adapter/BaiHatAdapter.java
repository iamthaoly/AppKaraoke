package com.iamthaoly.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.iamthaoly.appkaraoke.MainActivity;
import com.iamthaoly.appkaraoke.R;
import com.iamthaoly.model.BaiHat;

import org.w3c.dom.Text;

public class BaiHatAdapter extends ArrayAdapter<BaiHat> {
    Activity context;
    int resource;

    public BaiHatAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource, null);
        TextView txtMa = view.findViewById(R.id.txtMa);
        TextView txtTen = view.findViewById(R.id.txtTen);
        TextView txtCasy = view.findViewById(R.id.txtCaSy);
        final ImageView imgLike = view.findViewById(R.id.imgLike);
        final ImageView imgDislike = view.findViewById(R.id.imgDislike);
        final BaiHat baiHat = getItem(position);
        txtMa.setText(baiHat.getMa());
        txtTen.setText(baiHat.getTen());
        txtCasy.setText(baiHat.getCasy());
        if(baiHat.getThich() == 1)
        {
            imgLike.setVisibility(View.VISIBLE);
            imgDislike.setVisibility(View.INVISIBLE);
        }
        else
        {
            imgLike.setVisibility(View.INVISIBLE);
            imgDislike.setVisibility(View.VISIBLE);
        }
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLike.setVisibility(View.INVISIBLE);
                imgDislike.setVisibility(View.VISIBLE);
                xuLyDislike(baiHat);
            }
        });
        imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgDislike.setVisibility(View.INVISIBLE);
                imgLike.setVisibility(View.VISIBLE);
                xuLyLike(baiHat);
            }
        });
        return view;
    }

    private void xuLyDislike(BaiHat baiHat) {
        ContentValues values = new ContentValues();
        values.put("YEUTHICH", 0);
        int ketQua = MainActivity.database.update(
                MainActivity.TableName,
                values,
                "MABH=?",
                new String[]{baiHat.getMa()});
        if (ketQua > 0) {
            Toast.makeText(context, "Đã gỡ bỏ bài hát [" + baiHat.getTen() + "] khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
            if(MainActivity.selectedTab == 1)
                remove(baiHat);
        }

    }

    private void xuLyLike(BaiHat baiHat) {
        ContentValues values = new ContentValues();
        values.put("YEUTHICH", 1);
        int ketQua = MainActivity.database.update(
                MainActivity.TableName, 
                values, 
                "MABH=?", 
                new String[]{baiHat.getMa()});
        if (ketQua > 0)
            Toast.makeText(context, "Đã thêm bài hát [" + baiHat.getTen() + "] vào danh sách yêu thích", Toast.LENGTH_SHORT).show();

    }
}
