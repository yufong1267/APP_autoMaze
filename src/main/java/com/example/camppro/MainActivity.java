package com.example.camppro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    private Button player;
    private float PLAYER_SHIFT = 25;
    private  String ans = "";
    private float test = 0;
    private TextView test1 = null;
    private Button u , d , l , r;
    private String ANSWER = "rrrrruuuurrrrrrrdddrrruuuuuuuuuuuurrrrruuuuurrddrruuu00";
    private int index_reg = 0;
    private boolean check_out = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        player = (Button) findViewById(R.id.player);

        //初始化玩家位址
        player.setY(1200);


        test1 = (TextView)findViewById(R.id.test);
        u = (Button)findViewById(R.id.up1);
        d = (Button)findViewById(R.id.down1);
        l = (Button)findViewById(R.id.left1);
        r = (Button)findViewById(R.id.right1);
        Check_Position();


        final Handler handler = new Handler();





            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (ANSWER.charAt(index_reg))
                    {
                        case 'u':
                            player.setY(player.getY()- PLAYER_SHIFT);
                            test1.setText("" + player.getX() + " , " + player.getY());
                            Check_Position();
                            break;
                        case 'd':
                            player.setY(player.getY()+ PLAYER_SHIFT);
                            test1.setText("" + player.getX() + " , " + player.getY());
                            Check_Position();
                            break;
                        case 'r':
                            player.setX(player.getX()+ PLAYER_SHIFT);
                            test1.setText("" + player.getX() + " , " + player.getY());
                            Check_Position();
                            break;
                        case 'l':
                            player.setX(player.getX()- PLAYER_SHIFT);
                            test1.setText("" + player.getX() + " , " + player.getY());
                            Check_Position();
                            break;
                    }
                    index_reg++;
                    Win_Check();
                    if (ANSWER.charAt(index_reg) == '0')
                    {
                        check_out = false;
                    }
                    handler.postDelayed(this, 300);
                }
            }, 300);
        }




    public void left(View v){
        player.setX(player.getX()- PLAYER_SHIFT);
        test1.setText("" + player.getX() + " , " + player.getY());
        Check_Position();
        Win_Check();
    }
    public void right(View v){
        player.setX(player.getX()+ PLAYER_SHIFT);
        test1.setText("" + player.getX() + " , " + player.getY());
        Check_Position();
        Win_Check();
    }
    public void up(View v){
        player.setY(player.getY()- PLAYER_SHIFT);
        test1.setText("" + player.getX() + " , " + player.getY());
        Check_Position();
        Win_Check();
    }
    public void down(View v){
        player.setY(player.getY()+ PLAYER_SHIFT);
        test1.setText("" + player.getX() + " , " + player.getY());
        Check_Position();
        Win_Check();
    }

    private void reset(){
        u.setEnabled(true);
        d.setEnabled(true);
        l.setEnabled(true);
        r.setEnabled(true);
    }

    private void Check_Position(){
        //auto all visible
        reset();
        if (player.getY() >= 1200)
        {
            d.setEnabled(false);
        }
        if (player.getY() <= 0)
        {
            u.setEnabled(false);
        }
        if (player.getX() <= 0)
        {
            l.setEnabled(false);
        }
        if (player.getX() >= 650)
        {
            r.setEnabled(false);
        }
    }
    private void Win_Check(){
        if ((player.getY() >= 700 && player.getY() <= 750) && (player.getX() >= 600 && player.getX() <= 650))
        {
            Toast.makeText(this,"過關", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(3000);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } catch (InterruptedException e) {

            }
        }
    }
}
