package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    LottieAnimationView animator1;
    RecyclerView animator2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View myView = inflater.inflate(R.layout.fragment_placeholder, container, false);

        animator1 = myView.findViewById(R.id.loading_lottie);
        animator2 = myView.findViewById(R.id.view_List);

        animator2.setLayoutManager(new LinearLayoutManager(getContext()));
        animator2.setHasFixedSize(true);
        animator2.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        MyListAdapter mAdapter = new MyListAdapter();
        mAdapter.setList();
        animator2.setAdapter(mAdapter);
        animator1.setVisibility(View.VISIBLE);
        animator2.setVisibility(View.INVISIBLE);
        return myView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation alphaAnimator1 = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimator1.setInterpolator(new LinearInterpolator());
                alphaAnimator1.setDuration(1000);
                alphaAnimator1.setRepeatCount(ObjectAnimator.RESTART - 1);
                AlphaAnimation alphaAnimator2 = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimator2.setInterpolator(new LinearInterpolator());
                alphaAnimator2.setDuration(1000);
                alphaAnimator2.setRepeatCount(ObjectAnimator.RESTART - 1);

                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                animator1.startAnimation(alphaAnimator1);
                animator2.startAnimation(alphaAnimator2);
                alphaAnimator1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animator1.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                alphaAnimator2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animator2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        }, 5000);
    }

}
