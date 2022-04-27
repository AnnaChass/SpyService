package com.example.mobile2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobile2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import androidx.work.*;
import 	android.content.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    final String LOG_TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finish();

        while (true) {
            OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
            WorkManager.getInstance(getApplicationContext()).enqueue(myWorkRequest);
            try {
                Thread.sleep(3000);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}