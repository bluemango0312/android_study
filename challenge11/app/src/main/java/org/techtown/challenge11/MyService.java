package org.techtown.challenge11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    public MyService(){

    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if(intent == null){
            return Service.START_STICKY;
        } else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private void processCommand(Intent intent){
        String contents = intent.getStringExtra("contents");

        Intent output = new Intent(getApplicationContext(), MainActivity.class);
        output.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        output.putExtra("contents", contents);

        startActivity(output);
    }


}
