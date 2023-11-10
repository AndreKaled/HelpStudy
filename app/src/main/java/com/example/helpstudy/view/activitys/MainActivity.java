package com.example.helpstudy.view.activitys;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.helpstudy.R;
import com.example.helpstudy.utils.Musica;
import com.example.helpstudy.view.fragments.CronometroFragment;
import com.example.helpstudy.view.fragments.FlashCardFragment;
import com.example.helpstudy.view.fragments.ListaFragment;
import com.example.helpstudy.view.fragments.PerfilFragments;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        replaceFragment(new CronometroFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.cronometro:
                    replaceFragment(new CronometroFragment());
                    break;
                case R.id.listas:
                    replaceFragment(new ListaFragment());
                    break;
                case R.id.flashcards:
                    replaceFragment(new FlashCardFragment());
                    break;
                case R.id.conquistass:
                    replaceFragment(new PerfilFragments());
                    break;
            }

            return true;
        });

    }

    //outside on create


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}