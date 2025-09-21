package com.example.pokemon;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PokemonViewHolder> {
    private List<PokemonModel> lista;
    public Adapter(List<PokemonModel> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemons, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonModel pokemon = lista.get(position);

        if (pokemon.getGif_front_url() != null && !pokemon.getGif_front_url().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .asGif()
                    .load(pokemon.getGif_front_url())
                    .into(holder.img_pokemon);
        }

        if (!pokemon.getTipos().isEmpty()) {
            String tipoPrincipal = pokemon.getTipos().get(0);
            int cor = Cores.TYPE_COLORS.getOrDefault(tipoPrincipal, Color.LTGRAY);
            holder.cardBackground.setBackgroundColor(cor);
        } else {
            holder.cardBackground.setBackgroundColor(Color.LTGRAY);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), Pokemon.class);
            intent.putExtra("id", pokemon.getId());
            intent.putExtra("nome", pokemon.getNome());
            intent.putExtra("gif_front_url", pokemon.getGif_front_url());
            intent.putExtra("gif_back_url", pokemon.getGif_back_url());
            intent.putExtra("front_default_url", pokemon.getFront_default());
            intent.putExtra("altura", pokemon.getAltura());
            intent.putExtra("peso", pokemon.getPeso());
            intent.putExtra("curiosidade", pokemon.getCuriosidade());
            intent.putStringArrayListExtra("tipos", new ArrayList<>(pokemon.getTipos()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView img_pokemon;
        View cardBackground;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pokemon = itemView.findViewById(R.id.img_pokemon);
            cardBackground = itemView.findViewById(R.id.background);
        }
    }
}