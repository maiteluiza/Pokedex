package com.example.pokemon;

import android.graphics.Color;
import java.util.HashMap;
import java.util.Map;

public class Cores {
    public static final Map<String, Integer> TYPE_COLORS = new HashMap<>();
    static {
        TYPE_COLORS.put("normal", Color.parseColor("#A8A77A"));
        TYPE_COLORS.put("fire", Color.parseColor("#EE8130"));
        TYPE_COLORS.put("water", Color.parseColor("#6390F0"));
        TYPE_COLORS.put("electric", Color.parseColor("#F7D02C"));
        TYPE_COLORS.put("grass", Color.parseColor("#7AC74C"));
        TYPE_COLORS.put("ice", Color.parseColor("#96D9D6"));
        TYPE_COLORS.put("fighting", Color.parseColor("#C22E28"));
        TYPE_COLORS.put("poison", Color.parseColor("#A33EA1"));
        TYPE_COLORS.put("ground", Color.parseColor("#E2BF65"));
        TYPE_COLORS.put("flying", Color.parseColor("#A98FF3"));
        TYPE_COLORS.put("psychic", Color.parseColor("#F95587"));
        TYPE_COLORS.put("bug", Color.parseColor("#A6B91A"));
        TYPE_COLORS.put("rock", Color.parseColor("#B6A136"));
        TYPE_COLORS.put("ghost", Color.parseColor("#735797"));
        TYPE_COLORS.put("dragon", Color.parseColor("#6F35FC"));
        TYPE_COLORS.put("steel", Color.parseColor("#B7B7CE"));
        TYPE_COLORS.put("dark", Color.parseColor("#705746"));
        TYPE_COLORS.put("fairy", Color.parseColor("#D685AD"));
    }
}