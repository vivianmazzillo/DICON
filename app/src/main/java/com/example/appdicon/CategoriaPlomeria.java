package com.example.appdicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoriaPlomeria extends AppCompatActivity {

    private TextView mprecio1, mtitulo1;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_plomeria);

        recyclerView = findViewById(R.id.recyclerview);
        items = new ArrayList<>();
        items.add("Precio");
        items.add("Precio");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items);
        recyclerView.setAdapter(adapter);


        mprecio1  = (TextView) findViewById(R.id.precio1);
        mtitulo1 = (TextView) findViewById(R.id.titulo1);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Categorías").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    String Precio = dataSnapshot.child("Precio").getValue().toString();
                    String Nombre = dataSnapshot.child("Nombre").getValue().toString();
                    mprecio1.setText("" + Precio);
                    mtitulo1.setText(""+ Nombre);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void menuprincipal(View view){
        startActivity(new Intent(this,MenuActivity.class));
        finish();
    }
}
