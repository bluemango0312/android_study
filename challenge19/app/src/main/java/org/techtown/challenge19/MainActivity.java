package org.techtown.challenge19;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    WebView webView;

    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);
        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new ViewClient());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void makeRequest(){
        String url = editText.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
                webView.loadUrl(url);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);

    }

    private class ViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url){
            view.loadUrl(url);
            return true;
        }
    }


}