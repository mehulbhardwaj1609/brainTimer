package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton ;
    TextView sumTextView;
    Button button0,button1,button2,button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int positionOfAnswer;
    TextView resultTextView,scoreTextView;
    int score = 0;
    int position = 0;
    TextView timerTextView;

    public void chooseAnswer(View view){
        if(Integer.toString(positionOfAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }
        else{
            resultTextView.setText("wrong:(");
        }
        position++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(position));
        newQuestion();
    }

    public void goButton(View view){
        goButton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
            }
        }.start();

    }


    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        positionOfAnswer = random.nextInt(4);

        for(int i = 0; i < 4 ; i++){
            if(i == positionOfAnswer)
            {
                answers.add(a+b);

            }
            else
            {
                int wronganswer = random.nextInt(41);
                while (wronganswer == a+b){
                    wronganswer = random.nextInt(41);
                }
                answers.add(wronganswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        newQuestion();



    }
}