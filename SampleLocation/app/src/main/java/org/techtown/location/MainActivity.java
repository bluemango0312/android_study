package org.techtown.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });

        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_COARSE_LOCATION,
                        Permission.ACCESS_FINE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        showToast("허용된 권한 개수 : " + data.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        showToast("거부된 권한 개수 : " + data.size());
                    }
                })
                .start();
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //매니저 객체 참조

        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER); //이전에 확인했던 위치 정보 가져오기
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude : " + longitude;

                textView.setText(message);
            }

            GPSListener gpsListener = new GPSListener(); //리스너 객체 생성
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener); //위치 요청
            Toast.makeText(getApplicationContext(), "내 위치확인 요청함", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener{
        public void onLocationChanged(Location location){ //위치가 확인되었을 때 자동 호출
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message = "내 위치 -> Latitude : " + latitude + "\nLongitude : " + longitude;
            textView.setText(message);
        }

        public void onProviderDisabled(String provider){}
        public void onProviderEnabled(String provider){}
        public void onStatusChanged(String provider, int status, Bundle extras){}
    }
}