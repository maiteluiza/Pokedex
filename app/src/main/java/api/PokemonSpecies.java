package api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokemonSpecies {

    @SerializedName("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;

    public List<FlavorTextEntry> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public static class FlavorTextEntry {
        @SerializedName("flavor_text")
        private String flavorText;
        private Language language;

        public String getFlavorText() {
            return flavorText;
        }

        public Language getLanguage() {
            return language;
        }
    }

    public static class Language {
        private String name;

        public String getName() {
            return name;
        }
    }
}
