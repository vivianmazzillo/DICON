package com.example.appdicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoriaElectricos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_electricos);
    }

    public void menuprincipal(View view){
        startActivity(new Intent(this,MenuActivity.class));
        finish();
    }
}
