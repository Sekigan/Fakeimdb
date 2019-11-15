package com.its.fakeimdb;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity  {



    FirebaseAuth mAuth;
    private RecyclerView recyclerView;


    private Adapter adapter;
    ImageView acc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

            setUpRecyclerView();




        //adapter.setOnItemClickListener(MainScreen.this);

        acc = findViewById(R.id.btn);
        acc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view ) {
                switch (view.getId()) {
                    case R.id.btn:
                        cambio();
                        break;

                }

            }});


    }


    private void setUpRecyclerView() {
        Query query = FirebaseFirestore.getInstance()
                .collection("Peliculas")
                .orderBy("año")
                .limit(10);

        FirestoreRecyclerOptions<pelicula> options=new FirestoreRecyclerOptions.Builder<pelicula>()
                .setQuery(query, pelicula.class)
                .build();

        adapter=new Adapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @SuppressWarnings("SpellCheckingInspection")
    private void goToLogin(){
        Intent intend = new Intent(MainScreen.this, login1.class);
        startActivity(intend);

    }
    private void cambio(){
        Intent intend = new Intent(MainScreen.this, Login2.class);
        startActivity(intend);

    }

    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


  /*  public void onItemClick(int position) {
        Intent intent = new Intent(MainScreen.this, detail.class);

        pelicula p = peliculaArrayList.get(position);
        intent.putExtra(EXTRA_TITULO, p.getTitulo());
        intent.putExtra(EXTRA_GENERO, p.getGenero());
        intent.putExtra(EXTRA_ANO, p.getAño());
        intent.putExtra(EXTRA_ACTORES, p.getActores());
        intent.putExtra(EXTRA_IMAGEN, p.getImagen());
        startActivity(intent);

    }*/
}
