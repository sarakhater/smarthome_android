package com.iot.smarthomemasterandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.iot.smarthomemasterandroid.databinding.ActivityMainBinding;
import com.iot.smarthomemasterandroid.ui.AsyncResponse;
import com.iot.smarthomemasterandroid.remote.AuthorizationSharedHelper;
import com.iot.smarthomemasterandroid.remote.SensorSyncRespose;
import com.iot.smarthomemasterandroid.ui.SmartHomeAsyncTask;
import com.iot.smarthomemasterandroid.remote.SmartHomeIView;
import com.iot.smarthomemasterandroid.remote.SmartHomePresenter;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SmartHomeIView {

     final String SALT_OTP = "d55e7b88-757d-11ec-90d6-0242ac120003";
    int currentId = 0;
    String currentType = "Electric Meter";
    String currentAction = "reading";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    DrawerLayout drawer;
    private SmartHomePresenter presenter = new SmartHomePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
presenter.attachView(this);
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                System.out.println(destination);
                if(destination.getLabel().equals("Electric Meter")){
                    System.out.println("Electric here");
                    currentId  = 0;
                    currentType = "Electric Meter";

                }
                else  if(destination.getLabel().equals("Water Meter")){
                    System.out.println("Water Meter here");
                    currentId  = 1;
                    currentType = "Water Meter";
                }
                else  if(destination.getLabel().equals("Gas Meter")){
                    System.out.println("Gas Meter here");
                    currentId  = 2;
                    currentType = "Gas Meter";

                }
                connectToServer();
            }
        });

    }

   /* {
        "message": "string",
            "data": true
    }*/
    public void connectToServer()  {
        Map<String,Object> map = new HashMap<>();

        map.put("id", currentId);
        map.put("type", currentType);
        map.put("action",currentAction);

        JSONObject jsonObj = new JSONObject(map);
        System.out.println(jsonObj);
        String sha256Hash = getSha256Hash(SALT_OTP + jsonObj);
        AuthorizationSharedHelper.setAuthorizationHash(this,sha256Hash);
        presenter.syncSensor(currentId ,currentType ,currentAction );

        //SmartHomeAsyncTask task = new SmartHomeAsyncTask(map,this);
       // task.execute("http://localhost:8090/api/" + "Sensors/Sync");

        SmartHomeAsyncTask callApi=new SmartHomeAsyncTask(map,this , new AsyncResponse() {
            @Override
            public void processFinish(String callbackResponse) {
                Log.d("Callback response ->", callbackResponse);
                Toast.makeText(MainActivity.this, callbackResponse , Toast.LENGTH_LONG).show();

            }
        });
        callApi.execute("http://localhost:8090/api/" + "Sensors/Sync");

    }



    public static String getSha256Hash(String password) {
        try {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            digest.reset();
            return bin2hex(digest.digest(password.getBytes()));
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String bin2hex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b : data)
            hex.append(String.format("%02x", b & 0xFF));
        return hex.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public Activity activity() {
        return this;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(SensorSyncRespose response) {
        System.out.println(response);
        Toast.makeText(this,response.toString() , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable e) {
        System.out.println(e);
    }

    @Override
    public void onSuccess(Object o) {
        System.out.println(o);
        Toast.makeText(this,o.toString() , Toast.LENGTH_LONG).show();

    }
}