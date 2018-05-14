package com.ryadita.www.tgs_datamahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrasiData (View view) {
        Intent intent1 = new Intent(this, RegistrasiActivity.class);
        startActivity(intent1);
    }

    public void lihatData (View view) {
        Intent intent2 = new Intent(this, LihatActivity.class);
        startActivity(intent2);
    }
}

