package com.example.helpstudy;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item))
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.closed);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.configuracoes:
                    {
                        Toast.makeText(MainActivity.this, "Configurações", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.backup:
                    {
                        Toast.makeText(MainActivity.this, "Backup", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.sobreNos:
                    {
                        Toast.makeText(MainActivity.this, "Sobre nós", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
                return false;
            }
        });





    }
}