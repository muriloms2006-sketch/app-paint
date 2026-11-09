package com.app.paint;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DesenhoView desenho;

    Button btnPreto;
    Button btnVermelho;
    Button btnAzul;
    Button btnVerde;
    Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desenho = findViewById(R.id.desenho);

        btnPreto = findViewById(R.id.btnPreto);
        btnVermelho = findViewById(R.id.btnVermelho);
        btnAzul = findViewById(R.id.btnAzul);
        btnVerde = findViewById(R.id.btnVerde);
        btnLimpar = findViewById(R.id.btnLimpar);

        btnPreto.setOnClickListener(v -> {
            desenho.mudarCor(Color.BLACK);
        });

        btnVermelho.setOnClickListener(v -> {
            desenho.mudarCor(Color.RED);
        });

        btnAzul.setOnClickListener(v -> {
            desenho.mudarCor(Color.BLUE);
        });

        btnVerde.setOnClickListener(v -> {
            desenho.mudarCor(Color.GREEN);
        });

        btnLimpar.setOnClickListener(v -> {
            desenho.limpar();
        });
    }
}

