package com.example.appdicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }
                  //botones

    public void buttonPress(View view){

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    public void plomeria(View view){
        startActivity(new Intent(this,CategoriaPlomeria.class));
        finish();
    }

    public void electricos(View view){
        startActivity(new Intent(this,CategoriaElectricos.class));
        finish();
    }
}
