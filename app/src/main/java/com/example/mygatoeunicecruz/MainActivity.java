package com.example.mygatoeunicecruz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOnePun, playerTwoPun, playerWin;
    private Button[] buttons = new Button[9];
    private Button rein;

    private int playerOneCon, playerTwoCon, rondaCon;
    boolean tjugador;

    int [] estadoJuego={2,2,2,2,2,2,2,2,2};

    int [][] posGana = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8},{2,4,6}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOnePun = (TextView) findViewById(R.id.playerOnePun);
        playerTwoPun = (TextView) findViewById(R.id.playerTwoPun);
        playerWin = (TextView) findViewById(R.id.playerWin);
        rein = (Button) findViewById(R.id.rein);

        for (int i=0; i < buttons.length; i++){
            String buttonID = "btn"+i;
            int resID=getResources().getIdentifier(buttonID, "id",getPackageName());
            buttons[i]=(Button) findViewById(resID);
            buttons[i].setOnClickListener(this);
        }
        rondaCon=0;
        playerOneCon=0;
        playerTwoCon=0;
        tjugador=true;

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID =v.getResources().getResourceEntryName(v.getId());
        int estadoJuegoP = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));
        if (tjugador){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFFF"));
            estadoJuego[estadoJuegoP] = 0;
        }else{
            ((Button)v).setText("0");
            ((Button)v).setTextColor(Color.parseColor("#000000"));
            estadoJuego[estadoJuegoP] = 1;
        }
        rondaCon++;
        if(checkGan()){
            if (tjugador){
                playerOneCon++;
                actPun();
                Toast.makeText(this,"Gano el jugador 1", Toast.LENGTH_SHORT).show();
                nueRonda();
            }else{
                playerTwoCon++;
                actPun();
                Toast.makeText(this,"Gano el jugador 2", Toast.LENGTH_SHORT).show();
                nueRonda();

            }

        }else if(rondaCon ==9 ){
            nueRonda();
            Toast.makeText(this,"Empate", Toast.LENGTH_SHORT).show();

        }else{
            tjugador= !tjugador;
        }
    }
    public boolean checkGan(){
        boolean ganRond=false;

        for (int [] winningPosion : posGana){
            if(estadoJuego[winningPosion[0]]== estadoJuego[winningPosion[1]] &&
                    estadoJuego[winningPosion[1]]==estadoJuego[winningPosion[2]] && estadoJuego[winningPosion[0]]!=2){
                ganRond = true;
            }
        }
        return ganRond;
    }
    public void actPun(){
        playerOnePun.setText(Integer.toString(playerOneCon));
        playerTwoPun.setText(Integer.toString(playerTwoCon));
    }
    public void nueRonda(){
        rondaCon =0;
        tjugador=true;

        for (int i =0; i<buttons.length; i++){
            estadoJuego[i]=2;
            buttons[i].setText("");
        }

    }
}