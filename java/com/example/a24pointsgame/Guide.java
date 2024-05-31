package com.example.a24pointsgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Guide extends AppCompatActivity {

        //private String[] data = { "学习模式玩法", "拓展模式玩法", "挑战模式玩法"};
        private List<Function> functionList = new ArrayList<Function>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guide);
            initFunctions(); // 初始化水果数据
            FunctionAdapter adapter = new FunctionAdapter(Guide.this, R.drawable.function_item, functionList);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            builder.setMessage("已知一副扑克牌有54张，去除大王和小王，剩余52张。在学习模式中，您可以点击选择任意4张牌，并点击获取结果按钮。系统将利用加减乘除进行计算得到24。在本模式中，您可以学习用1-13计算24点的技巧和方法。")
                                    .setTitle("学习模式——24点表达式")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .create()
                                    .show();
                            break;
                        case 1:
                            builder.setMessage("本模式在学习模式的基础上进行了拓展。您可以点击选择任意4张牌，并输入您想要计算得到的点数。通过点击获取结果按钮，系统将利用加减乘除进行计算得到该结果。在本模式中，您可以学习用1-13计算任意点的技巧和方法。")
                                    .setTitle("拓展模式——任意点的表达式")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .create()
                                    .show();
                            break;
                        case 2:
                            builder.setMessage("本模式供您进行相应的挑战。您在选择任意4张牌后，通过在文本框中输入符合规定的表达式，如果输入正确，将为您进行加分。您需要注意输入的格式！")
                                    .setTitle("挑战模式——进行挑战游戏")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .create()
                                    .show();
                            break;
                    }
                }
            });
        }
    private void initFunctions() {
        Function f1 = new Function("学习模式", R.drawable.f1);
        functionList.add(f1);
        Function f2 = new Function("拓展模式", R.drawable.f2);
        functionList.add(f2);
        Function f3 = new Function("挑战模式", R.drawable.f3);
        functionList.add(f3);
    }

}


