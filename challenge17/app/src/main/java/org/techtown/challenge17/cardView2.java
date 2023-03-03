package org.techtown.challenge17;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class cardView2 extends LinearLayout {

    ImageView imageView;
    TextView textView;
    TextView textView2;
    TextView textView3;


    public cardView2(Context context) {
        super(context);
        init(context);
    }

    public cardView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cardview2, this, true);
        imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.number);
        TextView textView3 = findViewById(R.id.city);
    }

}
