package com.example.whichbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //instance variables we need for widget
    private Button buttonLeft;
    private TextView textViewScore;
    private Button buttonRight;
    private int leftNumber;
    private int rightNumber;
    private int score;
    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();

        randomizeAndUpdateDisplay();

    }

    private void randomizeAndUpdateDisplay() {
        //set score
        String scoreString = getResources().getString(R.string.textview_main_score);
        textViewScore.setText(scoreString + score);

        //randomize numbers
        leftNumber = genNumbers();
        rightNumber = genNumbers();
        while(leftNumber == rightNumber) {
            rightNumber = genNumbers();
        }

        //set button values
        buttonLeft.setText(String.valueOf(leftNumber));
        buttonRight.setText(String.valueOf(rightNumber));
    }

    private int genNumbers() {
        int range = MAX_NUM - MIN_NUM + 1;
        return MIN_NUM + (int)(Math.random() * range);
    }

    private void wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_left);
        buttonRight = findViewById(R.id.button_main_right);
        textViewScore = findViewById(R.id.textview_main_score);
    }

    // this matches the name of the method in the button's XML onClick atribute
    public void onLeftClick(View view) {
        checkAnswer(true);

    }
    public void onRightClick(View view) {
        checkAnswer(false);
    }
    public void checkAnswer(boolean leftpressed){
        String message;
        if((leftNumber > rightNumber && leftpressed) || (rightNumber > leftNumber && !leftpressed)){
            score++;
            message = "Correct!";

        }else{

            score--;
            message = "LessCorrect!";
        }
        randomizeAndUpdateDisplay();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }
}
