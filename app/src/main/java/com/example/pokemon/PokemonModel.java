package com.example.pokemon;

import java.util.List;

public class PokemonModel {
    private int id;
    private String nome;
    private String gif_front_url;
    private String gif_back_url;
    private String front_default;
    private int altura;
    private int peso;
    private String curiosidade;

    private List<String> tipos;

    public PokemonModel(String nome, String gif_front_url, List<String> tipos) {
        this.nome = nome;
        this.gif_front_url = gif_front_url;
        this.tipos = tipos;
    }

    public PokemonModel(int id, String nome, String gif_front_url, String gif_back_url, String front_default,
                        int altura, int peso, String curiosidade, List<String> tipos) {
        this.id = id;
        this.nome = nome;
        this.gif_front_url = gif_front_url;
        this.gif_back_url = gif_back_url;
        this.front_default = front_default;
        this.altura = altura;
        this.peso = peso;
        this.curiosidade = curiosidade;
        this.tipos = tipos;
    }

    public String getNome() {
        return nome;
    }

    public String getGif_front_url() {
        return gif_front_url;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public int getId() {
        return id;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public String getCuriosidade() {
        return curiosidade;
    }

    public String getGif_back_url() {
        return gif_back_url;
    }

    public String getFront_default() {
        return front_default;
    }

    public void setCuriosidade(String curiosidade) {
        this.curiosidade = curiosidade;
    }

}
