package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.Model.JarDetail;
import com.example.moneymanager.Model.User;
import com.example.moneymanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    int idCurrentLoginUser;






    ActivityMainBinding bingding;
    Toolbar tooltopbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bingding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(bingding.getRoot());
        ////////////////////////////////////////////////////
        SharedPreferences prefs = getSharedPreferences("getIdUser", MODE_PRIVATE);
        idCurrentLoginUser = prefs.getInt("idUserCurrent", -1);
        Toast.makeText(this, "idUser" + idCurrentLoginUser, Toast.LENGTH_SHORT).show();
        ////////////////////////////////////////////////////


        replaceFracment(new HomeFragment());
//        bingding.bottomNavagationView.setBackground(null);
        bingding.bottomNavagationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.homeIcon:
                    replaceFracment(new HomeFragment());
                    break;
                case R.id.addIcon:
                    Intent myIntent = new Intent(MainActivity.this, AddIncomeActivity.class);
                    myIntent.putExtra("userId",idCurrentLoginUser);
                    startActivity(myIntent);
                    break;
                case R.id.persionIcon:
                    replaceFracment(new PersionFragment());
                    break;
            }
            return true;
        });
        setControl();
        setEvent();
    }

    private void replaceFracment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void setEvent() {

        setSupportActionBar(tooltopbar);
        //set name toolbar
        setNameToolBar();

    }

    private void setNameToolBar() {
        User user = new User();
        DatabaseHelper data = new DatabaseHelper(MainActivity.this);
        user = data.getUserInfo(idCurrentLoginUser);
        tooltopbar.setSubtitle(user.getFullname());
    }

    private void setControl() {
        tooltopbar = findViewById(R.id.tool_top_bar);
    }
}