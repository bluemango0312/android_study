package org.techtown.drawer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.drawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    page1 fragment1;
    page2 fragment2;
    page3 fragment3;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        fragment1 = new page1();
        fragment2 = new page2();
        fragment3 = new page3();
        
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    }
    
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
    

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu1){
            Toast.makeText(this, "첫 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelect(0, null);
        } else if(id == R.id.menu2){
            Toast.makeText(this, "두 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelect(1, null);
        } else if(id == R.id.menu3){
            Toast.makeText(this, "세 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
            onFragmentSelect(2, null);
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentSelect(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 0){
            curFragment = fragment1;
            toolbar.setTitle("첫 번째 화면");
        } else if (position == 1){
            curFragment = fragment2;
            toolbar.setTitle("두 번째 화면");
        } else if (position == 2){
            curFragment = fragment3;
            toolbar.setTitle("세 번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }
}