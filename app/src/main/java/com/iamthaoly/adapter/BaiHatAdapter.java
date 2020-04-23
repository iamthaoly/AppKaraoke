package com.iamthaoly.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        ImageView imgLike = view.findViewById(R.id.imgLike);
        ImageView imgDislike = view.findViewById(R.id.imgDislike);
        BaiHat baiHat = getItem(position);
        txtMa.setText(baiHat.getMa());
        txtTen.setText(baiHat.getTen());
        txtCasy.setText(baiHat.getCasy());
        if(baiHat.getThich() == 1)
        {
            imgLike.setVisibility(View.INVISIBLE);
            imgDislike.setVisibility(View.VISIBLE);
        }
        else
        {
            imgLike.setVisibility(View.VISIBLE);
            imgDislike.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
