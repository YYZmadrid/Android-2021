package com.example.chapter3.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private ImageView myPicture;
    private TextView myName;
    private TextView mySignature;

    public ListViewHolder(@NonNull View itemView){
        super(itemView);
        myName = itemView.findViewById(R.id.name);
        mySignature = itemView.findViewById(R.id.signature);
        myPicture = itemView.findViewById(R.id.picture);
    }

    public static ListViewHolder create(Context context, ViewGroup root){
        View v = LayoutInflater.from(context).inflate(R.layout.itemlist, root, false);
        return new ListViewHolder(v);
    }

    public void bind(String name, String signature, int position){
        myPicture.setImageResource(R.mipmap.ic_launcher);
        myName.setText(name);
        mySignature.setText(signature);
    }
}
