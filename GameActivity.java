package com.example.blanca;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.blanca.GameView;

public class GameActivity extends Activity {
    TextView score_show;

    GameView gv;
    TextView new_game;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            int num=msg.arg1;
            score_show.setText(num+"");
        }
    };

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_show=(TextView) findViewById(R.id.tv_score_show);
        gv=(GameView) findViewById(R.id.gv_show);
        new_game=(TextView)findViewById(R.id.tv_newgame);
        new_game.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                gv.GameStart();
                gv.score=0;
            }
        });

        Timer timer=new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Message msg = new Message();
                msg.arg1 = gv.score;
                handler.sendMessage(msg);
            }
        }, 80 , 150);
        score_show.setText(100+"");

    }


}
