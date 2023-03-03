package org.techtown.challenge16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = true;

    EditText editText;
    WebView webView;
    LinearLayout layout;
    Button button2;
    Animation translateLeft;
    Animation translateRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.urlText);
        webView = findViewById(R.id.WebView);
        layout = findViewById(R.id.topLayout);
        button2 = findViewById(R.id.button2);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new ViewClient());

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.go_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.go_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateRight.setAnimationListener(animListener);
        translateLeft.setAnimationListener(animListener);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText.getText().toString());
                layout.startAnimation(translateRight);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.startAnimation(translateLeft);
                layout.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);
            }
        });
    }

    private class ViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url){
            view.loadUrl(url);
            return true;
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                button2.setVisibility(View.VISIBLE);
                layout.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            } else{
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}