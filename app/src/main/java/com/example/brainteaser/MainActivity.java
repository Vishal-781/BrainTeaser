package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView result;
    TextView pointsTextView;
    TextView sumText;
    int locationOfCorrectAnswer;
    int score=0;
    Button option1,option2,option3,option4;
    TextView timer;
    Button restart;
//    GridLayout grid;
    ConstraintLayout everything ;
    int n=0;


    public void generateQuestion()
    {
        result.setText("");
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b= rand.nextInt(21);
         answers.clear();
        sumText.setText(Integer.toString(a)+" + "+Integer.toString(b));
        int c = a+b;
        locationOfCorrectAnswer=rand.nextInt(3);

        int  inc;
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(c);

            }
            else {
                inc=rand.nextInt(40);
                while(inc==c)
                    inc= rand.nextInt(40);
                answers.add(inc);
            }
        }
        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view)
    {
        Log.i("Tag!",view.getTag().toString());


        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            result.setText("Correct!");
          Log.i("info",result.toString());
          score++;
        }
        else {
            result.setText("Incorrect!");

        }

//        result.setVisibility(View.VISIBLE);
        n++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(n));
       generateQuestion();
    }
    public  void start(View veiw)
    {
         startbutton.setVisibility(View.INVISIBLE);
         everything.setVisibility(View.VISIBLE);

         restart(startbutton);

    }
    public void restart(View view)
    {
        score=0;
        n=0;
        generateQuestion();
        timer.setText("30s");
        result.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                int s=(int) l / 1000;
                timer.setText(Integer.toString(s)+"s");
            }

            @Override
            public void onFinish() {
                result.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(n));
                result.setVisibility((View.VISIBLE));
                restart.setVisibility(View.VISIBLE);
//                startbutton.setVisibility(View.VISIBLE);

            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton=findViewById(R.id.button2);
         sumText=(TextView)findViewById(R.id.sum);
         timer=findViewById(R.id.timer);
        result=findViewById(R.id.correct);

        pointsTextView=findViewById(R.id.pontsText);
        option1 =findViewById(R.id.option1);
        option2 =(Button) findViewById(R.id.option2);
         option3 =(Button) findViewById(R.id.option3);
         option4 =(Button) findViewById(R.id.option4);
         restart=findViewById(R.id.rButton);
      everything=findViewById(R.id.everything);
      everything.setVisibility(View.INVISIBLE);
        restart(restart);






    }
}