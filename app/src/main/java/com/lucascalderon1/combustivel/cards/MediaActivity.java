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

public class MediaActivity extends AppCompatActivity {
    private AdView adView;


    private ImageButton ib_voltar;
    private TextInputEditText editDistancia, editLitros;
    private TextView textResultado, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        adView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        textResultado = findViewById(R.id.textResultado);

        ib_voltar = findViewById(R.id.ib_voltar);
        textView4 = findViewById(R.id.textView4);
        editDistancia = findViewById(R.id.editDistancia);
        editLitros = findViewById(R.id.editLitros);
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
                "1 - Com o carro na reserva você deve encher o tanque completamente e preencher o valor abastecido no primeiro campo.\n" +
                        "2 - Agora rode até chegar na reserva novamente e preencha a quantidade de Km que rodou no segundo campo. \n" +
                        "3 - No terceiro, digite o valor do preço do combustivel por litro.  \n" +
                        "4 - Ao final clique em CALCULAR, assim tendo o retorno de quanto o seu veiculo rende por litro de combustivel.");

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

        if (!validarCampos()) {
            return;
        }


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String distanciaPercorrida = editDistancia.getText().toString();
        String litrosAbastecidos = editLitros.getText().toString();


        Double valorAlcool = Double.parseDouble(distanciaPercorrida);
        Double valorGasolina = Double.parseDouble(litrosAbastecidos);

        Double resultado = valorAlcool / valorGasolina;
        DecimalFormat df2 = new DecimalFormat("#,##0.00");
        String resultadoFormatado = df2.format(resultado);



        textResultado.setText("Seu veículo está fazendo " + resultadoFormatado + " km por litro de combustível.");



    }

    private boolean validarCampos() {
        String distanciaPercorrida = editDistancia.getText().toString();
        String litrosAbastecidos = editLitros.getText().toString();

        if (distanciaPercorrida.isEmpty()) {
            editDistancia.setError("Campo obrigatório!");
            return false;
        }

        if (litrosAbastecidos.isEmpty()) {
            editLitros.setError("Campo obrigatório!");
            return false;
        }

        return true;
    }


    public void limpar(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        editLitros.setText("");
        editDistancia.setText("");
        textResultado.setText("");
        textView4.setText("");

    }
}

