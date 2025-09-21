package com.example.pokemon;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import api.PokeApi;
import api.PokemonDados;

import api.PokemonSpecies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<PokemonModel> listaPokemons;
    private PokeApi pokeApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        listaPokemons = new ArrayList<>();
        adapter = new Adapter(listaPokemons);
        recyclerView.setAdapter(adapter);

        configurarRetrofit();
        gerarPokemonsAleatorios(80);
    }

    private void gerarPokemonsAleatorios(int quantidade) {
        Random random = new Random();
        Set<Integer> usados = new HashSet<>();

        for (int i = 0; i < quantidade; i++) {
            int id = random.nextInt(1025) + 1;
            if (!usados.contains(id)) {
                usados.add(id);

                pokeApiService.getPokemonData(id).enqueue(new Callback<PokemonDados>() {
                    @Override
                    public void onResponse(Call<PokemonDados> call, Response<PokemonDados> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            PokemonDados data = response.body();
                            List<String> tipos = new ArrayList<>();
                            for (PokemonDados.TypeSlot typeSlot : data.getTypes()) {
                                tipos.add(typeSlot.getType().getName());
                            }
                            String gif_front_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/" + id + ".gif";
                            String gif_back_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/" + id + ".gif";
                            String front_default_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
                            PokemonModel pokemonCompleto = new PokemonModel(id, data.getName(), gif_front_url, gif_back_url, front_default_url, data.getHeight(), data.getWeight(), "", tipos);

                            listaPokemons.add(pokemonCompleto);
                            adapter.notifyItemInserted(listaPokemons.size() - 1);

                            pokeApiService.getPokemonSpecies(id).enqueue(new Callback<PokemonSpecies>() {
                                @Override
                                public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        PokemonSpecies species = response.body();
                                        List<PokemonSpecies.FlavorTextEntry> entries = species.getFlavorTextEntries();

                                        String curiosidade = "Curiosidade não encontrada."; // Valor padrão
                                        if (entries != null) {
                                            for (PokemonSpecies.FlavorTextEntry entry : entries) {
                                                curiosidade = entry.getFlavorText().replace("\n", " ").replace("\f", " ");
                                                break;
                                            }
                                        }

                                        for (PokemonModel p : listaPokemons) {
                                            if (p.getId() == id) {
                                                p.setCuriosidade(curiosidade);
                                                adapter.notifyDataSetChanged();
                                                break;
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonDados> call, Throwable t) {
                        // Tratar falhas
                    }
                });

            } else {
                i--;
            }
        }
    }

    private void configurarRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeApiService = retrofit.create(PokeApi.class);
    }
}