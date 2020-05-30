package com.example.appdicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoriaPlomeria extends AppCompatActivity {

    private RecyclerView serviciosplomeria;
    private DatabaseReference categoriasRef, electricosRef;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_plomeria);
        categoriasRef = FirebaseDatabase.getInstance().getReference().child("Categorías");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        serviciosplomeria = (RecyclerView) findViewById(R.id.recyclerview);
        serviciosplomeria.setHasFixedSize(true);
        serviciosplomeria.setLayoutManager(linearLayoutManager);

        displayAllCategorias();
    }


    public void displayAllCategorias() {

        FirebaseRecyclerOptions<Categorias> options =
                new FirebaseRecyclerOptions.Builder<Categorias>()
                        .setQuery(categoriasRef.child("Plomería"), new SnapshotParser<Categorias>() {
                            @NonNull
                            @Override
                            public Categorias parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Categorias(snapshot.child("Nombre").getValue().toString(),
                                        snapshot.child("Precio").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Categorias, CategoriaElectricos.CategoriasViewHolder>(options) {

            @Override
            protected void onBindViewHolder (@NonNull final CategoriaElectricos.CategoriasViewHolder holder,
                                             int position, @NonNull Categorias model){
                holder.setName(model.getNombre());
                holder.setPrecio(model.getPrecio());
            };

            @NonNull
            @Override
            public CategoriaElectricos.CategoriasViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                                                int viewType){
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.all_categorias_display_layout, parent, false);

                return new CategoriaElectricos.CategoriasViewHolder(view);
            }

        };

        serviciosplomeria.setAdapter(adapter);
    }


    public void menuprincipal(View view){
        startActivity(new Intent(this,MenuActivity.class));
        finish();
    }

    public void ordenarProducto(View v) {
        Toast.makeText(v.getContext(),"Su orden fue tomada con exito",Toast.LENGTH_LONG).show();
    }
}
