package com.example.a24pointsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameActivity3 extends AppCompatActivity {
    private List<ImageButton> chooseCards = new ArrayList<ImageButton>();
    private String[] chooseCardsName ={"", "", "", ""};
    private Stack<Integer> sta = new Stack<Integer>();
    private Stack<ImageButton> sta2 = new Stack<ImageButton>();

    public EditText et1;

    private boolean isHave;

    private int fl5=1;

    private String curCardName = "";
    private int curCardBackId = 0;
    private int curCardNum = 0;
    private int curCardId = 0;
    private final PointX pointX = new PointX();
    private int[] chooseNums = {0, 0, 0, 0};
    private ImageButton card1 = null;
    private ImageButton card2 = null;
    private ImageButton card3 = null;
    private ImageButton card4 = null;

    private List<String> equs = pointX.getPointXEqu(chooseNums);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        Button backBnt1 = findViewById(R.id.game1_back);
        backBnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameActivity3.this, MainActivity.class));
            }
        });

        //et1 = findViewById(R.id.et1);
        et1=(EditText) this.findViewById(R.id.et1);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        chooseCards.add(card1);
        chooseCards.add(card2);
        chooseCards.add(card3);
        chooseCards.add(card4);
    }


    /**
     * 获取24点表达式
     * @param view
     */
    public void getTips(View view){
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 获取24点表达式
        StringBuilder msg = new StringBuilder();
        String inputText = et1.getText().toString();
        if(inputText.length()==0){
            fl5=0;
            System.out.println("11");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("未输入目标数");
            builder.setMessage("请重新输入!");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        else {
            fl5=1;
            PointX.TARGET = Integer.parseInt(inputText);
            isHave = false;
            if (chooseNums[0] == 0 || chooseNums[1] == 0 || chooseNums[2] == 0 || chooseNums[3] == 0) {
                System.out.println("12");
                msg = new StringBuilder("请先选择4张卡片。");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

            }
            else {
                List<String> equs = pointX.getPointXEqu(chooseNums);
                if (equs.isEmpty()) {
                    msg = new StringBuilder("当前选择的四张卡片不能构成" + PointX.TARGET + "点表达式。");
                } else {
                    int cnt = equs.size();
                    for (int i = 0; i < cnt; i++) {
                        msg.append(i + 1).append("、").append(equs.get(i)).append("\n");
                    }
                    msg.append("共有 " + cnt + " 个表达式");
                    isHave = true;
                }
            }
        }
        Drawable icon = null;
        if(isHave){
            icon = getResources().getDrawable(R.drawable.ok);
        }else{
            icon = getResources().getDrawable(R.drawable.alert);
        }

        // 创建弹窗
        if(fl5==1) {    //设置fl5的原因在于防止和未输入数字时的产生的弹窗发生冲突
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(icon)
                    .setTitle(PointX.TARGET + "点表达式")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setMessage(msg.toString())
                    .create()
                    .show();
        }
    }


    /**
     * 清空输入的卡片以及运算符
     * @param view
     */
    public void clearCards(View view){
        // 清空卡牌框
        Drawable quesBack = getResources().getDrawable(R.drawable.question1);
        card1.setBackground(quesBack);
        card2.setBackground(quesBack);
        card3.setBackground(quesBack);
        card4.setBackground(quesBack);

        // 清空数组
        chooseNums[0] = chooseNums[1] = chooseNums[2] = chooseNums[3] = 0;
        chooseCardsName[0] = chooseCardsName[1] = chooseCardsName[2] = chooseCardsName[3] = "";
        sta.clear();
        while(!sta2.empty()){
            ImageView bnt = sta2.pop();
            bnt.setVisibility(View.VISIBLE);
        }
//        tips.setText(welcome);
    }

    /**
     * 回退卡片
     * @param view
     */
    public void backCards(View view){
        if(!sta.empty()){
            int index = sta.pop();
            chooseCards.get(index).setBackground(getResources().getDrawable(R.drawable.question1));
            chooseNums[index] = 0;
            chooseCardsName[index] = "";
        }
        if(!sta2.empty()){
            ImageButton bnt = sta2.pop();
            bnt.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 点击选择按钮
     * @param view
     */
    public void insertCard(View view) {
        ImageButton bnt = findViewById(R.id.card1);

        // 判断是在哪一个空格
        if (view.getId() == R.id.card1) {
            chooseNums[0] = curCardNum;
        } else if (view.getId() == R.id.card2) {
            chooseNums[1] = curCardNum;
        } else if (view.getId() == R.id.card3) {
            chooseNums[2] = curCardNum;
        } else if (view.getId() == R.id.card4) {
            chooseNums[3] = curCardNum;
        }

        // 更换背景
        view.setBackground(getResources().getDrawable(curCardBackId));
    }

    /**
     * 根据卡片名称获取点数
     * @param cardName
     * @return
     */
    public int getCardNum(String cardName) {
        int num = 0;
        switch (cardName.charAt(cardName.length() - 1)) {
            case 'a':
                num = 1;
                break;
            case '2':
                num = 2;
                break;
            case '3':
                num = 3;
                break;
            case '4':
                num = 4;
                break;
            case '5':
                num = 5;
                break;
            case '6':
                num = 6;
                break;
            case '7':
                num = 7;
                break;
            case '8':
                num = 8;
                break;
            case '9':
                num = 9;
                break;
            case '0':
                num = 10;
                break;
            case 'j':
                num = 11;
                break;
            case 'q':
                num = 12;
                break;
            case 'k':
                num = 13;
                break;
            default:
                num = 0;
                break;
        }
        return num;

    }

    /**
     * 选择卡片
     * @param view
     */
    @SuppressLint("NonConstantResourceId")
    public void chooseCard(View view) {

        curCardId = view.getId();

        // 获取当前选取牌的名称
        switch (view.getId()) {
            case R.id.cluba:  curCardBackId = R.drawable.cluba; curCardName = "cluba"; break;
            case R.id.club2:  curCardBackId = R.drawable.club2; curCardName = "club2"; break;
            case R.id.club3:  curCardBackId = R.drawable.club3; curCardName = "club3"; break;
            case R.id.club4:  curCardBackId = R.drawable.club4; curCardName = "club4"; break;
            case R.id.club5:  curCardBackId = R.drawable.club5; curCardName = "club5"; break;
            case R.id.club6:  curCardBackId = R.drawable.club6; curCardName = "club6"; break;
            case R.id.club7:  curCardBackId = R.drawable.club7; curCardName = "club7"; break;
            case R.id.club8:  curCardBackId = R.drawable.club8; curCardName = "club8"; break;
            case R.id.club9:  curCardBackId = R.drawable.club9; curCardName = "club9"; break;
            case R.id.club10:  curCardBackId = R.drawable.club10; curCardName = "club10"; break;
            case R.id.clubj:  curCardBackId = R.drawable.clubj; curCardName = "clubj"; break;
            case R.id.clubq:  curCardBackId = R.drawable.clubq; curCardName = "clubq"; break;
            case R.id.clubk:  curCardBackId = R.drawable.clubk; curCardName = "clubk"; break;
            case R.id.hearta:  curCardBackId = R.drawable.hearta; curCardName = "hearta"; break;
            case R.id.heart2:  curCardBackId = R.drawable.heart2; curCardName = "heart2"; break;
            case R.id.heart3:  curCardBackId = R.drawable.heart3; curCardName = "heart3"; break;
            case R.id.heart4:  curCardBackId = R.drawable.heart4; curCardName = "heart4"; break;
            case R.id.heart5:  curCardBackId = R.drawable.heart5; curCardName = "heart5"; break;
            case R.id.heart6:  curCardBackId = R.drawable.heart6; curCardName = "heart6"; break;
            case R.id.heart7:  curCardBackId = R.drawable.heart7; curCardName = "heart7"; break;
            case R.id.heart8:  curCardBackId = R.drawable.heart8; curCardName = "heart8"; break;
            case R.id.heart9:  curCardBackId = R.drawable.heart9; curCardName = "heart9"; break;
            case R.id.heart10:  curCardBackId = R.drawable.heart10; curCardName = "heart10"; break;
            case R.id.heartj:  curCardBackId = R.drawable.heartj; curCardName = "heartj"; break;
            case R.id.heartq:  curCardBackId = R.drawable.heartq; curCardName = "heartq"; break;
            case R.id.heartk:  curCardBackId = R.drawable.heartk; curCardName = "heartk"; break;
            case R.id.spadea:  curCardBackId = R.drawable.spadea; curCardName = "spadea"; break;
            case R.id.spade2:  curCardBackId = R.drawable.spade2; curCardName = "spade2"; break;
            case R.id.spade3:  curCardBackId = R.drawable.spade3; curCardName = "spade3"; break;
            case R.id.spade4:  curCardBackId = R.drawable.spade4; curCardName = "spade4"; break;
            case R.id.spade5:  curCardBackId = R.drawable.spade5; curCardName = "spade5"; break;
            case R.id.spade6:  curCardBackId = R.drawable.spade6; curCardName = "spade6"; break;
            case R.id.spade7:  curCardBackId = R.drawable.spade7; curCardName = "spade7"; break;
            case R.id.spade8:  curCardBackId = R.drawable.spade8; curCardName = "spade8"; break;
            case R.id.spade9:  curCardBackId = R.drawable.spade9; curCardName = "spade9"; break;
            case R.id.spade10:  curCardBackId = R.drawable.spade10; curCardName = "spade10"; break;
            case R.id.spadej:  curCardBackId = R.drawable.spadej; curCardName = "spadej"; break;
            case R.id.spadeq:  curCardBackId = R.drawable.spadeq; curCardName = "spadeq"; break;
            case R.id.spadek:  curCardBackId = R.drawable.spadek; curCardName = "spadek"; break;
            case R.id.diamonda:  curCardBackId = R.drawable.diamonda; curCardName = "diamonda"; break;
            case R.id.diamond2:  curCardBackId = R.drawable.diamond2; curCardName = "diamond2"; break;
            case R.id.diamond3:  curCardBackId = R.drawable.diamond3; curCardName = "diamond3"; break;
            case R.id.diamond4:  curCardBackId = R.drawable.diamond4; curCardName = "diamond4"; break;
            case R.id.diamond5:  curCardBackId = R.drawable.diamond5; curCardName = "diamond5"; break;
            case R.id.diamond6:  curCardBackId = R.drawable.diamond6; curCardName = "diamond6"; break;
            case R.id.diamond7:  curCardBackId = R.drawable.diamond7; curCardName = "diamond7"; break;
            case R.id.diamond8:  curCardBackId = R.drawable.diamond8; curCardName = "diamond8"; break;
            case R.id.diamond9:  curCardBackId = R.drawable.diamond9; curCardName = "diamond9"; break;
            case R.id.diamond10:  curCardBackId = R.drawable.diamond10; curCardName = "diamond10"; break;
            case R.id.diamondj:  curCardBackId = R.drawable.diamondj; curCardName = "diamondj"; break;
            case R.id.diamondq:  curCardBackId = R.drawable.diamondq; curCardName = "diamondq"; break;
            case R.id.diamondk:  curCardBackId = R.drawable.diamondk; curCardName = "diamondk"; break;
            default: curCardBackId = 0; curCardName = ""; break;
        }

        curCardNum = getCardNum(curCardName);


        // 判断之前有没有选择该张卡片
        for(int i = 0; i<4; i++){
            if(chooseCardsName[i].equals(curCardName)) return;
        }

        //从四个空格中选择一个
        for(int i = 0; i<4; i++){
            if(chooseNums[i] == 0){
                sta.push(i);
//                ImageButton bnt = ;
                sta2.push(findViewById(curCardId));
                view.setVisibility(View.INVISIBLE);
                chooseCards.get(i).setBackground(getResources().getDrawable(curCardBackId));
                chooseNums[i] = curCardNum;
                chooseCardsName[i] = curCardName;
                break;
            }
        }
    }
}