package com.its.fakeimdb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class Adapter extends FirestoreRecyclerAdapter<pelicula, Adapter.AdapterHolder> {
    private OnItemClickListener listener;
    private Context context;
    public static final String EXTRA_TITULO="titulo";
    public static final String EXTRA_GENERO="genero";
    public static final String EXTRA_ANO="año";
    public static final String EXTRA_IMAGEN="imagen";
    public static final String EXTRA_ACTORES="actores";


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public Adapter(@NonNull FirestoreRecyclerOptions<pelicula> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterHolder holder, int position, @NonNull pelicula model) {

        holder.textViewTitulo.setText(model.getTitulo());
        holder.textViewactores.setText(model.getActores());
        holder.textViewaño.setText(model.getAño());
        holder.textViewGenero.setText(model.getGenero());
        String uri = model.getImagen(); // or whatever you want
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.imageViewPoster);


    }


    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista,parent,false);

        return new AdapterHolder(view);
    }

    class AdapterHolder extends RecyclerView.ViewHolder{
        TextView textViewTitulo, textViewaño, textViewGenero, textViewactores;
        ImageView imageViewPoster;
        public AdapterHolder(View itemview){
            super(itemview);
            textViewTitulo=itemview.findViewById(R.id.textViewTitulo);
            textViewaño=itemview.findViewById(R.id.textViewaño);
            textViewactores=itemview.findViewById(R.id.textViewActores);
            textViewGenero=itemview.findViewById(R.id.textViewGenero);
            imageViewPoster=itemview.findViewById(R.id.imgPeli);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                        Intent intent = new Intent(context, detail.class);
                        pelicula p = getSnapshots().getSnapshot(position).toObject(pelicula.class);

                        intent.putExtra(EXTRA_TITULO, p.getTitulo());
                        intent.putExtra(EXTRA_GENERO, p.getGenero());
                        intent.putExtra(EXTRA_ANO, p.getAño());
                        intent.putExtra(EXTRA_ACTORES, p.getActores());
                        intent.putExtra(EXTRA_IMAGEN, p.getImagen());
                        context.startActivity(intent);

                    }
                }
            });




        }
    }

}
