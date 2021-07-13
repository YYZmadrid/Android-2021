package com.example.myapplication.recycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public List<TestData> mDataset = new ArrayList<>();
    private IOnItemClickListener mItemClickListener;

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.onBind(position, mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }

        });
    }

    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {
        void onItemCLick(int position, TestData data);
        void onItemLongCLick(int position, TestData data);
    }

    public MyAdapter(List<TestData> myDataset) {
        mDataset.addAll(myDataset);
    }
    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void changeData(int position, TestData data){
        mDataset.set(position, data);
        Collections.sort(mDataset);
        notifyItemRangeChanged(0, mDataset.size());
    }

    public void addData() {
        TestData data1 = new TestData("新建热搜", "0");
        int position = getItemCount();
        mDataset.set(position-1, data1);
        notifyItemChanged(position-1);
        TestData data2 = new TestData("单击此处添加热搜", "0");
        mDataset.add(position, data2);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        if (null != mDataset && mDataset.size() > position && mDataset.size() > 1) {
            mDataset.remove(position);
            notifyItemRemoved(position);
            if (position != mDataset.size()) {
                notifyItemRangeChanged(position, mDataset.size() - position);
            }
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIndex;
        private TextView tvTitle;
        private TextView tvHot;
        private View contentView;


        public MyViewHolder(View v) {
            super(v);
            contentView = v;
            tvIndex = v.findViewById(R.id.tv_index);
            tvTitle = v.findViewById(R.id.tv_title);
            tvHot = v.findViewById(R.id.tv_hot);
        }

        public void onBind(int position, TestData data) {
            tvIndex.setText(new StringBuilder().append(position).append(".  ").toString());
            tvTitle.setText(data.title);
            tvHot.setText(data.hot);
            if(position == 0){
              tvIndex.setTextColor(Color.parseColor("#DC143C"));
            } else if (position < 4 && position > 0) {
                tvIndex.setTextColor(Color.parseColor("#FFD700"));
            } else {
                tvIndex.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }
}
