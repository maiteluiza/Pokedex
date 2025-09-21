package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApi {
    @GET("pokemon/{id}")
    Call<PokemonDados> getPokemonData(@Path("id") int id);

    @GET("pokemon-species/{id}/")
    Call<PokemonSpecies> getPokemonSpecies(@Path("id") int id);
}