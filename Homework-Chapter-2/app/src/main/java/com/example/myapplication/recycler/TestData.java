package com.example.myapplication.recycler;

public class TestData implements Comparable<TestData>{

    String title;
    String hot;
    public TestData(String title, String hot) {
        this.title = title;
        this.hot = hot;
    }

    @Override
    public int compareTo(TestData data) {
        // TODO Auto-generated method stub
        return Integer.valueOf(data.hot) - Integer.valueOf(this.hot);
    }


}