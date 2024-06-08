package com.example.blanca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.blanca.Card;

public class GameView extends GridLayout{
    private Card cards[][]=new Card[4][4];
    private Card[][] cardsMap = new Card[4][4];
    private List<Point>emptyCards=new ArrayList<>();
    Random rd=new Random();
    int score=0;

    public GameView(Context context){
        super(context);
        //TODO Auto-generated constructor stub
        initGame();
    }
    public GameView(Context context,AttributeSet attrs){
        super(context,attrs);
        //TODO Auto-generated constructor stub
        initGame();
    }
    public GameView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        //TODO Auto-generated constructor stub
        initGame();
    }

    private void initGame(){
        setColumnCount(4);
        setBackgroundColor(0xffffcccc);

        setOnTouchListener(new OnTouchListener() {
            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case  MotionEvent.ACTION_UP:
                        offsetX = event.getX();
                        offsetY = event.getY();

                        if (Math.abs(offsetX)>Math.abs(offsetY)){
                            if (offsetX<-5){
                                swipeLeft();
                            } else if (offsetX>5) {
                                swipeRight();
                            }
                        } else{
                            if (offsetY<-5){
                                swipeUp();
                            } else if (offsetY>5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void swipeLeft(){

        for (int y = 0;y<4;y++){
            for(int x = 0;x<4;x++){
                for (int x1 = x+1;x1<4;x1++){
                    if (cardsMap[x1][y].getNum()>0){
                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            break;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void swipeRight(){

        for (int y = 0;y<4;y++){
            for(int x = 3;x>=0;x--){
                for (int x1 = x-1;x1>=0;x1--){
                    if (cardsMap[x1][y].getNum()>0){
                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            break;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeUp(){

        for (int x = 0;x<4;x++){
            for(int y = 0;y<4;y++){
                for (int y1 = y+1;y1<4;y1++){
                    if (cardsMap[x][y1].getNum()>0){
                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            break;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }
    private void swipeDown(){

        for (int x = 0;x<4;x++){
            for(int y = 3;y>=0;y--){
                for (int y1 = y-1;y1>=0;y1--){
                    if (cardsMap[x][y1].getNum()>0){
                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            break;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void moveRight() {
        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0;x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    //当同一行为空时，不需处理
                    if (cards[x1][y].getNum() > 0) {
                        if (cards[x][y].getNum() < 2) {
//将前一张卡片的值移动到当前卡片
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x1][y].setNum(0);
                            x++;
                            flage = true;
                            score += 2;
                        } else if (cards[x][y].getNum() == cards[x1][y].getNum()) {
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            score += cards[x][y].getNum();
                            cards[x1][y].setNum(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }

        if (flage) {
            creatRandomCard();
        }
    }
    private void moveLeft(){
        boolean flage = false;
        for(int y =0;y<4;y++){
            for(int x=0;x<4;x++){
                for(int x1=x+1;x1<4;x1++){
//当同一行为空时，不需处理
                    if(cards[x1][y].getNum()> 0) {
                        if (cards[x][y].getNum() < 2) {
//将前一张卡片的值移动到当前卡片
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x1][y].setNum(0);
                            x--;
                            flage = true;
                            score += 2;
                        } else if (cards[x][y].getNum() == cards[x1][y].getNum()) {
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            score += cards[x][y].getNum();
                            cards[x1][y].setNum(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }
        if(flage) {
            creatRandomCard();
        }
    }
    private void moveDown(){
        boolean flage = false;
        for(int x=0;x<4;x++){
            for (int y =3;y>=0;y--){
                for (int y1=y-1;y1>= 0;y1--){
//当同一行为空时，不需处理
                    if(cards[x][y1].getNum()>0){
                        if (cards[x][y].getNum()< 2) {
//将前一张卡片的值移动到当前卡片
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y1].setNum(0);
                            y++;
                            flage = true;
                            score += 2;
                        } else if(cards[x][y].getNum()== cards[x][y1].getNum()){
                            cards[x][y].setNum(cards[x][y].getNum()* 2);
                            score +=cards[x][y].getNum();
                            cards[x][y1].setNum(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }
        if(flage){
            creatRandomCard();
        }
    }
    private void moveUp(){
        boolean flage = false;
        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++){
                    if(cards[x][y1].getNum()> 0){
                        if(cards[x][y].getNum()<2){
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y1].setNum(0);
                            y--;
                            flage = true;
                            score+=2;
                        } else if(cards[x][y].getNum()== cards[x][y1].getNum()) {
                            cards[x][y].setNum(cards[x][y].getNum() * 2);
                            score += cards[x][y].getNum();
                            cards[x][y1].setNum(0);
                            flage = true;
                        }
                        break;}
                }
            }
        }
        if(flage) {
            creatRandomCard();
        }
    }
    //游戏结束判定以及判定处理
    private void Gameover(){
        boolean OverGame=true;
        for (int y=0;y<4;y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNum() <= 0 ||
                        (x > 0 && cards[x][y].getNum() ==
                                cards[x - 1][y].getNum()) ||
                        (x < 3 && cards[x][y].getNum() ==
                                cards[x + 1][y].getNum()) ||
                        (y > 0 && cards[x][y].getNum() ==
                                cards[x][y - 1].getNum()) ||
                        (y < 3 && cards[x][y].getNum() ==
                                cards[x][y + 1].getNum())) {
                    OverGame = false;
                }
            }
        }
        if(OverGame){
            new AlertDialog.Builder(getContext()).setTitle("hi").setMessage("again").
                    setPositiveButton("yes",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            //TODO Auto-generated method stub
                            GameStart();
                            score = 0;
                        }
                    }).setNegativeButton("No", null).show();
        }
    }
    //其他函数
    private void AddCard(int width, int height) {
        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                cards[x][y] = c;
                c.setNum(0);
                addView(c, width, height);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh) {
        //TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        int width = (w - 10) / 4;
        AddCard(width, width);
        GameStart();
    }
    private void creatRandomCard() {
        emptyCards.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cards[x][y].getNum() < 2) {
                    Point point = new Point(x, y);
                    emptyCards.add(point);
                }
            }
        }
        int selat = rd.nextInt(emptyCards.size());
        Point p = emptyCards.get(selat);
        emptyCards.remove(selat);
        int number = 0;
        if (rd.nextInt(10) > 4) {
            number = 4;
        } else
            number = 2;
        cards[p.x][p.y].setNum(number);
    }
    public void GameStart() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cards[x][y].setNum(0);
            }
        }
        creatRandomCard();
        creatRandomCard();
    }

}
