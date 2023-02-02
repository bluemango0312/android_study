package org.techtown.challenge15;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class clientInfo extends AppCompatActivity {
    LinearLayout clientLayout;
    Animation translateLeft;
    Animation translateRight;
    Button gotoHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_client_info);

        gotoHome = findViewById(R.id.button2);
        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        clientLayout = findViewById(R.id.clientLayout);
        clientLayout.startAnimation(translateLeft);

        gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientLayout.startAnimation(translateRight);

                new Handler().postDelayed(new Runnable() { //딜레이 걸어주기
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }, 200);

            }
        });

    }
}