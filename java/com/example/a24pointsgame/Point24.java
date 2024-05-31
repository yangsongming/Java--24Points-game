package com.example.a24pointsgame;

import static java.lang.Math.abs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Point24 {
    static int TARGET = 24;
    static final double EPSILON = 1e-5;
    static final int add = 0, mul = 1, sub = 2, div = 3;
    private List<String> equations = new ArrayList<String>();

    /**
     * 获取24点表达式
     * @param cards 4个卡牌点数
     * @return
     */


    public List<String> getPoint24Equ(int[] cards) {
        equations.clear();
        List<Double> list = new ArrayList<Double>();  // 存储操作数
        List<String> numStr = new ArrayList<String>();  // 存储表达式

        for (int num : cards) {
            list.add((double) num);
            numStr.add(Integer.toString(num));
        }

        p24(list, numStr);

        // 表达式去重
        equations = new ArrayList<String>(new LinkedHashSet<String>(equations));
        return equations;
    }

    /**
     * 回溯
     * @param list 数字列表
     * @param numStr 表达式列表
     */
    private void p24(List<Double> list, List<String> numStr) {
        if (list.size() == 0) {
            return;
        }

        if (list.size() == 1) {
            if (abs(list.get(0) - TARGET) < EPSILON) {
                String equ = numStr.get(0);
                equations.add(equ.substring(1, equ.length() - 1)+"="+TARGET);

            }
            return;
        }

        // 从数组中选取两个数字
        int n = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // 定义新的数组用于递归
                    List<Double> list2 = new ArrayList<Double>();
                    List<String> numStr2 = new ArrayList<String>();

                    // 将未选择的两个数加入新数组
                    for (int z = 0; z < n; z++) {
                        if (z != i && z != j) {
                            list2.add(list.get(z));
                            numStr2.add(numStr.get(z));
                        }
                    }
                    // 四个操作符
                    /*对四种可能的操作符（加法、乘法、减法、除法）进行遍历：
1.如果是加法操作，将两个数字相加，并将对应的字符串表达式添加到 numStr2。
2.如果是乘法操作，将两个数字相乘，并添加相应的字符串表达式。
3.如果是减法操作，将两个数字相减，并添加相应的字符串表达式。
4.如果是除法操作，首先检查除数是否为零（小于 EPSILON）。如果不是，执行除法操作并添加对应的字符串表达式。*/
                    for (int k = 0; k <= 3; k++) {
                        if (i < j && k <= 1)
                            continue;
                        if (k == add) {
                            list2.add(list.get(i) + list.get(j));
                            numStr2.add("(" + numStr.get(i) + "+" + numStr.get(j) + ")");

                        }
                        else if (k == mul) {
                            list2.add(list.get(i) * list.get(j));
                            numStr2.add("(" + numStr.get(i) + "*" + numStr.get(j) + ")");

                        }
                        else if (k == sub) {
                            list2.add(list.get(i) - list.get(j));
                            numStr2.add("(" + numStr.get(i) + "-" + numStr.get(j) + ")");

                        }
                        else if (k == div) {
                            // 判断除数是否为零
                            if (abs(list.get(j)) < EPSILON)
                                continue;
                            else {
                                list2.add(list.get(i) / list.get(j));
                                numStr2.add("(" + numStr.get(i) + "/" + numStr.get(j) + ")");
                            }
                        }

                        //在每次递归调用后，会将最后添加的元素从 list2 和 numStr2 中移除，以准备处理下一个操作符。
                        // 递归计算
                        p24(list2, numStr2);
                        list2.remove(list2.size() - 1);
                        numStr2.remove(numStr2.size() - 1);
                    }
                }
            }
        }
        return;
    }
}


