package com.example.pokemon;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class Pokemon extends AppCompatActivity {
    private ImageView gif_front, gif_back, front_default;
    private TextView txtId, txtNome, txtAltura, txtPeso, txtCuriosidade, txtTipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gif_front = findViewById(R.id.gif_front);
        gif_back = findViewById(R.id.gif_back);
        front_default = findViewById(R.id.front_default);
        txtId = findViewById(R.id.id_pokemon);
        txtNome = findViewById(R.id.nome_pokemon);
        txtAltura = findViewById(R.id.altura);
        txtPeso = findViewById(R.id.peso);
        txtCuriosidade = findViewById(R.id.curiosidade);
        txtTipos = findViewById(R.id.tipos);

        txtAltura.setTextColor(Color.WHITE);
        txtAltura.setShadowLayer(1.5f, -1, 1, Color.BLACK);
        txtPeso.setTextColor(Color.WHITE);
        txtPeso.setShadowLayer(1.5f, -1, 1, Color.BLACK);

        String nome = getIntent().getStringExtra("nome");
        String curiosidade = getIntent().getStringExtra("curiosidade");
        String gifFrontUrl = getIntent().getStringExtra("gif_front_url");
        String gifBackUrl = getIntent().getStringExtra("gif_back_url");
        String frontDefaultUrl = getIntent().getStringExtra("front_default_url");

        int id = getIntent().getIntExtra("id", -1);
        int altura = getIntent().getIntExtra("altura", -1);
        int peso = getIntent().getIntExtra("peso", -1);

        ArrayList<String> tiposList = getIntent().getStringArrayListExtra("tipos");
        String tipos = (tiposList != null && !tiposList.isEmpty()) ? TextUtils.join(", ", tiposList) : "-";

        txtNome.setText(nome != null ? nome : "-");
        txtId.setText(id != -1 ? String.valueOf(id) : "-");
        txtAltura.setText(altura != -1 ? String.valueOf(altura) : "-");
        txtPeso.setText(peso != -1 ? String.valueOf(peso) : "-");
        txtCuriosidade.setText(curiosidade != null ? curiosidade : "-");
        txtTipos.setText(tipos);

        if (gifFrontUrl != null && !gifFrontUrl.isEmpty()) {
            Glide.with(this).asGif().load(gifFrontUrl).into(gif_front);
        }

        if (gifBackUrl != null && !gifBackUrl.isEmpty()) {
            Glide.with(this).asGif().load(gifBackUrl).into(gif_back);
        }

        if (frontDefaultUrl != null && !frontDefaultUrl.isEmpty()) {
            Glide.with(this).load(frontDefaultUrl).into(front_default);
        }
    }
}