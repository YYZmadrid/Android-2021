package com.example.chapter3.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<String> nameList = new ArrayList<>();
    private List<String> signatureList = new ArrayList<>();

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ListViewHolder.create(parent.getContext(), parent);


    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(nameList.get(position), signatureList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void setList() {
        nameList.add("ly"); signatureList.add("here we go");
        nameList.add("ljt"); signatureList.add("fall");
        nameList.add("yyz"); signatureList.add("hala madrid");
        nameList.add("zzh"); signatureList.add("");
        nameList.add("jyc"); signatureList.add("");
        nameList.add("lcy"); signatureList.add("barca!");
        nameList.add("cz"); signatureList.add("");
        nameList.add("gc"); signatureList.add("");
        nameList.add("wjh"); signatureList.add("never say never");
        nameList.add("qaq"); signatureList.add("");
        nameList.add("ddd"); signatureList.add("");
    }
}


