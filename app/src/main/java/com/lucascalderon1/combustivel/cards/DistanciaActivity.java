package com.lucascalderon1.combustivel.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.home.HomeActivity;

import java.text.DecimalFormat;

public class DistanciaActivity extends AppCompatActivity {

    private AdView adView;

    private ImageButton ib_voltar;
    private TextInputEditText editDistancia, editLitros;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia);

        MobileAds.initialize(this, initializationStatus -> {

        });

        adView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        textResultado = findViewById(R.id.textResultado);
        ib_voltar = findViewById(R.id.ib_voltar);
        editDistancia = findViewById(R.id.editDistancia);
        editLitros = findViewById(R.id.editLitros);
        textResultado = findViewById(R.id.textResultado);
        ib_voltar = findViewById(R.id.ib_voltar);
        ib_voltar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            finish();
            startActivity(intent);
        });


    }


    public void abrirDialog(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(" Instruções");
        dialog.setMessage(
                "1 - No primeiro campo digite o rendimento que seu veículo faz por litro de combustível.\n" +
                        "2 - No segundo campo digite o total de litros abastecidos \n" +
                        "3 - Ao final clique em CALCULAR, assim tendo o retorno da distância que seu veículo percorrerá.");

        dialog.setIcon(R.drawable.ic_help);


        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Instruções minimizadas", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.create();
        dialog.show();

    }

    public void calcularPreco(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String distanciaPercorrida = editDistancia.getText().toString();
        String litrosAbastecidos = editLitros.getText().toString();

        if (distanciaPercorrida.isEmpty() || litrosAbastecidos.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Double valorAlcool = Double.parseDouble(distanciaPercorrida);
        Double valorGasolina = Double.parseDouble(litrosAbastecidos);

        Double resultado = valorAlcool * valorGasolina;

        DecimalFormat df2 = new DecimalFormat("#,##0.00");
        String resultadoFormatado = df2.format(resultado);

        textResultado.setText("Seu veículo percorrerá " + resultadoFormatado + " km");

    }


    public void limpar(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        editDistancia.setText("");
        editLitros.setText("");
        textResultado.setText("");

    }
}





