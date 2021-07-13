package com.example.myapplication.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("中国开展第12次北极科学考察", "5246002"));
        result.add(new TestData("港大不再承认学生会在校内的角色", "3920901"));
        result.add(new TestData("谷嘉诚 切走半块香皂 ", "1876439"));
        result.add(new TestData("丈夫迷恋女主播妻子带女儿投江", "1613049"));
        result.add(new TestData("请回答1988真的太好哭了", "1417835"));
        result.add(new TestData("比尔盖茨承认是自己搞砸了婚姻", "961420"));
        result.add(new TestData("梅桢放弃华东政法大学任教机会", "911916"));
        result.add(new TestData("张慧雯一秒猜出龚俊声音 ", "829602"));
        result.add(new TestData("医院弄错报告单患者吃错药3个月", "753576"));
        result.add(new TestData("黄渤 有这功夫还不如背词呢", "425290"));
        result.add(new TestData("单击此处添加热搜", "0"));
        return result;
    }

}