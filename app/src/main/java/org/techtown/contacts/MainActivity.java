package org.techtown.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        String[] permissions = {
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
        };

        checkPermission(permissions);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseContacts();
            }
        });
    }

    public void checkPermission(String[] permissions){
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i = 0; i < permissions.length; i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, curPermission + "권한 있음.", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this, curPermission + "권한 없음.", Toast.LENGTH_LONG).show();
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)){
                    Toast.makeText(this, curPermission + "권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else{
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        ActivityCompat.requestPermissions(this, targets, 101); //위험 권한 부여 요청하기
    }

    private void chooseContacts() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI); //연락처 화면을 띄우기 위한 인텐트 만들기
        startActivityForResult(contactPickerIntent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 101){
                try{
                    Uri contactsUri = data.getData();
                    String id = contactsUri.getLastPathSegment(); //선택한 연락처의 id 값 확인하기

                    getContacts(id);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void getContacts(String id) {
        Cursor cursor;
        String name = "";

        try{
            cursor = getApplicationContext().getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null, ContactsContract.Data.CONTACT_ID + "=?",
                    new String[] {id},
                    null);

            if(cursor.moveToFirst()){
                name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME));
                println("Name : " + name);

                String columns[] = cursor.getColumnNames();
                for(String column : columns){
                    int index = cursor.getColumnIndex(column);
                    String columnOutput = ("#" + index + " -> [" + column + "] " + cursor.getString(index));
                    println(columnOutput);
                }
                cursor.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void println(String data){
        textView.append(data+"\n");
    }
}