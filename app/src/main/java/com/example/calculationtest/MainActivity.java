package com.example.calculationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp() {

        System.out.println(controller.getCurrentDestination().getId()==R.id.questionFragment);
        if (controller.getCurrentDestination().getId()==R.id.questionFragment){
            System.out.println(1);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.quit_title);
            builder.setPositiveButton(R.string.quit_confirm,(dialog, which) -> {
                controller.navigateUp();
            });
            builder.setNegativeButton(R.string.quit_cancel,(dialog, which) -> {

            });
            builder.create().show();
        }else if (controller.getCurrentDestination().getId()==R.id.winFragment||controller.getCurrentDestination().getId()==R.id.loseFragment){
            System.out.println(2);
            controller.navigate(R.id.homeFragment);
        }else{
            System.out.println(3);
//            super.onBackPressed();
//            System.exit(0);
            finish();
        }

        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}
