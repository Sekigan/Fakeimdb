package com.its.fakeimdb;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.its.fakeimdb.Adapter.EXTRA_ACTORES;
import static com.its.fakeimdb.Adapter.EXTRA_ANO;
import static com.its.fakeimdb.Adapter.EXTRA_GENERO;
import static com.its.fakeimdb.Adapter.EXTRA_IMAGEN;
import static com.its.fakeimdb.Adapter.EXTRA_TITULO;


public class detail extends AppCompatActivity {
    TextView textViewTitulo, textViewaño, textViewactores, textViewGenero;
    ImageView imagepD;
    private RatingBar rate_bar;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent = getIntent();
        textViewTitulo=findViewById(R.id.textViewTituloD);
        textViewaño=findViewById(R.id.textViewañoD);
        textViewactores=findViewById(R.id.textViewActoresD);
        textViewGenero=findViewById(R.id.textViewGeneroD);
        imagepD=findViewById(R.id.imagepeD);

        String titulo = intent.getStringExtra(EXTRA_TITULO);
        String gen= intent.getStringExtra(EXTRA_GENERO);
        String añ = intent.getStringExtra(EXTRA_ANO);
        String ac = intent.getStringExtra(EXTRA_ACTORES);
        String img = intent.getStringExtra(EXTRA_IMAGEN);


        addListenerOnRatingBar();
        rate_bar=findViewById(R.id.ratingBar);
        rate_bar.setStepSize((float) 0.5);
        rate_bar.setMax(5);
        rate_bar.setRating(0.0f);

        textViewTitulo.setText(titulo);
        textViewaño.setText(añ);
        textViewactores.setText(ac);
        textViewGenero.setText(gen);
        Glide.with(this).load(String.valueOf(img)).into(imagepD);

    }

    private void addListenerOnRatingBar() {

        rate_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                Toast.makeText(detail.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
                    executeTransaction(rating);


            }

            private void executeTransaction(float rating) {
                notebookRef.document(EXTRA_TITULO).set(rating);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
