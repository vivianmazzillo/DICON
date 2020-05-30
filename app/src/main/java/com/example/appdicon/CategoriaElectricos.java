package com.example.appdicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CategoriaElectricos extends AppCompatActivity {

    private RecyclerView servicioselectricos;
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
        setContentView(R.layout.activity_categoria_electricos);

        categoriasRef = FirebaseDatabase.getInstance().getReference().child("Categorías");
        electricosRef = FirebaseDatabase.getInstance().getReference().child("Eléctricos");
        



        servicioselectricos = (RecyclerView) findViewById(R.id.servicios_electricos);
        servicioselectricos.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        servicioselectricos.setLayoutManager(linearLayoutManager);

        displayAllCategorias();
    }

    public void displayAllCategorias() {

        FirebaseRecyclerOptions<Categorias> options =
                new FirebaseRecyclerOptions.Builder<Categorias>()
                        .setQuery(categoriasRef.child("Eléctricos"), new SnapshotParser<Categorias>() {
                            @NonNull
                            @Override
                            public Categorias parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Categorias(snapshot.child("Nombre").getValue().toString(),
                                        snapshot.child("Precio").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Categorias, CategoriasViewHolder>(options) {

                    @Override
                    protected void onBindViewHolder (@NonNull final CategoriasViewHolder holder,
                    int position, @NonNull Categorias model){
                        holder.setName(model.getNombre());
                        holder.setPrecio(model.getPrecio());
                    };
                    
                    @NonNull
                    @Override
                    public CategoriasViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                    int viewType){
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.all_categorias_display_layout, parent, false);

                        return new CategoriasViewHolder(view);
                }

                };

        servicioselectricos.setAdapter(adapter);
    }

            public static class CategoriasViewHolder extends RecyclerView.ViewHolder {

                View mView;

                public CategoriasViewHolder(@NonNull View itemView) {
                    super(itemView);

                    mView = itemView;
                }

                public void setName(String name){
                    TextView nombre = (TextView) mView.findViewById(R.id.all_servicios_nombre);
                    nombre.setText(name);
                }

                public void setPrecio(String price){
                    TextView precio = (TextView) mView.findViewById(R.id.all_servicios_precio);
                    precio.setText(price);
                }
            }

            public void menuprincipal(View view) {
                startActivity(new Intent(this, MenuActivity.class));
                finish();
            }

            public void ordenarProducto(View v) {
                Toast.makeText(v.getContext(),"Su orden fue tomada con exito",Toast.LENGTH_LONG).show();
            }
}


