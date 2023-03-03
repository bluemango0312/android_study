package org.techtown.challenge17;


import static android.os.SystemClock.sleep;
import static android.view.View.INVISIBLE;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    LinearLayout layout2;
    TextView text;

    Animation goLeft;
    Animation gogoLeft;
    Handler handler = new Handler();
    boolean running = true;
    int open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        layout2 = findViewById(R.id.layout2);
        text = findViewById(R.id.textView2);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cardview, layout, true);

        LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater2.inflate(R.layout.cardview2, layout2, true);


        goLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.go_left);
        gogoLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gogo_left);

        AnimThread thread = new AnimThread();
        thread.start();


    }

    class AnimThread extends Thread{
        public void run(){
            open = 0;
            for(int i = 0;i<100;i++){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(open == 0){
                            layout2.startAnimation(goLeft);
                            layout.startAnimation(gogoLeft);
                            open = 1;
                            text.setText("2/2");

                        } else{
                            layout.startAnimation(goLeft);
                            layout2.startAnimation(gogoLeft);
                            open=0;
                            text.setText("1/2");
                        }
                    }
                });

                try{
                    sleep(5000);
                } catch(Exception e){

                }
            }

        }
    }
}