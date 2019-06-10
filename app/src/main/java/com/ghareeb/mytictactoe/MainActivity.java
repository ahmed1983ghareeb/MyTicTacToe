package com.ghareeb.mytictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

import model.GameMatrix;
import model.TTT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttons[][] = new ImageButton[3][3];
    TTT t = new TTT();
    ArrayList<GameMatrix> randumTurn = new ArrayList<>();

    int btnIds[][] = {
            {R.id.btn00,R.id.btn01,R.id.btn02},
            {R.id.btn10,R.id.btn11,R.id.btn12},
            {R.id.btn20,R.id.btn21,R.id.btn22}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void initialize(){

        for (int i = 0 ; i<3 ; i++)
            for(int j = 0; j <3; j++) {
                buttons[i][j] = findViewById(btnIds[i][j]);
                buttons[i][j].setOnClickListener(this);
            }
        generateRandumTurn();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn00:
                play(0,0);
                buttons[0][0].setEnabled(false);
                break;
            case R.id.btn01:
                play(0,1);
                buttons[0][1].setEnabled(false);
                break;
            case R.id.btn02:
                play(0,2);
                buttons[0][2].setEnabled(false);
                break;
            case R.id.btn10:
                play(1,0);
                buttons[1][0].setEnabled(false);
                break;
            case R.id.btn11:
                play(1,1);
                buttons[1][1].setEnabled(false);
                break;
            case R.id.btn12:
                play(1,2);
                buttons[1][2].setEnabled(false);
                break;
            case R.id.btn20:
                play(2,0);
                buttons[2][0].setEnabled(false);

                break;
            case R.id.btn21:
                play(2,1);
                buttons[2][1].setEnabled(false);
                break;
            case R.id.btn22:
                play(2,2);
                buttons[2][2].setEnabled(false);
                break;
        }

    }//end of onClick

    public void setBtnImage(char player, int btnRow, int btnCol){
        if (player == 'x')
            buttons[btnRow][btnCol].setImageResource(R.drawable.x_png);
            else
            buttons[btnRow][btnCol].setImageResource(R.drawable.o_png);
    }

    public void createAlert(String title, String msg){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        newGame();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void newGame(){
        t.initializeBorad();

        for (int i = 0 ; i<3 ; i++)
            for(int j=0; j<3; j++) {
                buttons[i][j].setImageResource(R.drawable.ic_launcher_background);
                buttons[i][j].setEnabled(true);
            }

        for (int i = 0; i<randumTurn.size();i++){
            randumTurn.remove(i);
        }
        generateRandumTurn();
    }

    public void play(int row, int col){
        char player;
        t.setRow(row);
        t.setCol(col);
        player = t.getPlayer();
        setBtnImage(player,row,col);
        for (int i = 0; i<randumTurn.size();i++){
            if (randumTurn.get(i).getCol() == col && randumTurn.get(i).getRow() == row){
                randumTurn.remove(i);
            }
        }
        if(!t.play())
            createAlert("Game Over", "Player " + player+" Wins!");
        else{
            computerPlay();
        }
    }

    public void computerPlay(){
        char player;
        Random random = new Random();
        int randumIndex = random.nextInt(randumTurn.size());
        t.setRow(randumTurn.get(0).getRow());
        t.setCol(randumTurn.get(0).getCol());
        player = t.getPlayer();
        setBtnImage(player,randumTurn.get(randumIndex).getRow(),randumTurn.get(randumIndex).getCol());
        buttons[randumTurn.get(randumIndex).getRow()][randumTurn.get(randumIndex).getCol()].setEnabled(false);
        if(!t.play())
            createAlert("Game Over", "Player " + player+" Wins!");
        randumTurn.remove(randumIndex);

    }

    public void generateRandumTurn() {

        randumTurn.add(new GameMatrix(0, 0));
        randumTurn.add(new GameMatrix(0, 1));
        randumTurn.add(new GameMatrix(0, 2));
        randumTurn.add(new GameMatrix(1, 0));
        randumTurn.add(new GameMatrix(1, 1));
        randumTurn.add(new GameMatrix(1, 2));
        randumTurn.add(new GameMatrix(2, 0));
        randumTurn.add(new GameMatrix(2, 1));
        randumTurn.add(new GameMatrix(2, 2));


    }
}
