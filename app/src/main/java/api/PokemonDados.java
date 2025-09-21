package api;

import java.util.List;

public class PokemonDados {
    private int id;
    private String name;
    private int height;
    private int weight;
    private Sprites sprites;
    private List<TypeSlot> types;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getHeight() { return height; }
    public int getWeight() { return weight; }
    public Sprites getSprites() { return sprites; }
    public List<TypeSlot> getTypes() { return types; }

    public static class TypeSlot {
        private Type type;
        public Type getType() { return type; }
    }

    public static class Type {
        private String name;
        public String getName() { return name; }
    }

    public static class Sprites {
        private String front_default;
        private String back_default;
        private OtherSprites other;

        public String getFrontDefault() { return front_default; }
        public String getBackDefault() { return back_default; }
        public OtherSprites getOther() { return other; }
    }

    public static class OtherSprites {
        private OfficialArtwork official_artwork;

        public OfficialArtwork getOfficialArtwork() { return official_artwork; }
    }

    public static class OfficialArtwork {
        private String front_default;
        public String getFrontDefault() { return front_default; }
    }
}
