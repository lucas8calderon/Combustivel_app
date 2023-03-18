package com.lucascalderon1.combustivel.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.home.HomeActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class QuantoVouGastarActivity extends AppCompatActivity {

    private AdView adView;

    private TextInputEditText editDistancia, editRendimento, editPrecoGasolina;
    private TextView textResultado, textView4;
    private ImageButton ib_voltar;

    private final String FORMATO_VALOR_REAL = "R$ #,##0.00";
    private final DecimalFormat decimalFormat = new DecimalFormat(FORMATO_VALOR_REAL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanto_vou_gastar);

        MobileAds.initialize(this, initializationStatus -> {

        });

        adView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        editDistancia = findViewById(R.id.editDistancia);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        editRendimento = findViewById(R.id.editRendimento);
        textResultado = findViewById(R.id.textResultado);
        editPrecoGasolina.addTextChangedListener(new MascaraValorReal(editPrecoGasolina));


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
                "1 - No primeiro campo, digite a distância a percorer.\n" +
                        "2 - No segundo, digite o rendimento por litro.\n" +
                        "3 - No terceiro, digite o valor do preço do combustivel por litro.\n" +
                        "3 - Ao final clique em CALCULAR, assim tendo retorno estimado que irá gastar em seu percurso ou viajem.\n" +
                        "4 - Cálculo usado para descobrir quanto irá gastar para percorrer um determinado trajeto.");

        dialog.setIcon(R.drawable.ic_help);


        dialog.setNegativeButton("OK", (dialog1, which) -> {

        });

        dialog.create();
        dialog.show();

    }

    @SuppressLint("SetTextI18n")
    public void calcularPreco(View view) {

        if (!validarCampos()) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String distanciaPercorridaStr = editDistancia.getText().toString().replaceAll("[^0-9.,]+", "");
        String litrosAbastecidosStr = editRendimento.getText().toString().replaceAll("[^0-9.,]+", "");
        String valorGasolinaStr = editPrecoGasolina.getText().toString().replaceAll("[^0-9.,]+", "");


        double distanciaPercorrida = Double.parseDouble(distanciaPercorridaStr);
        double litrosAbastecidos = Double.parseDouble(litrosAbastecidosStr);
        double valorGasolina = Double.parseDouble(valorGasolinaStr.replace(",", "."));

        double litros = distanciaPercorrida / litrosAbastecidos;
        double valorGasto = litros * valorGasolina;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        DecimalFormat dfLitros = new DecimalFormat("#,##0.00", symbols);
        DecimalFormat dfValor = new DecimalFormat("#,##0.00", symbols);

        String litrosFormatado = dfLitros.format(litros);
        String valorFormatado = dfValor.format(valorGasto);


        textResultado.setText("Você precisará de " + litrosFormatado + " litros \ne gastará aproximadamente R$ " + valorFormatado);

    }


    private boolean validarCampos() {
        if (TextUtils.isEmpty(editDistancia.getText().toString())) {
            editDistancia.setError("Campo obrigatório");
            editDistancia.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editRendimento.getText().toString())) {
            editRendimento.setError("Campo obrigatório");
            editRendimento.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editPrecoGasolina.getText().toString())) {
            editPrecoGasolina.setError("Campo obrigatório");
            editPrecoGasolina.requestFocus();
            return false;
        }

        return true;
    }


    public void limpar(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        editDistancia.setText("");
        editRendimento.setText("");
        editPrecoGasolina.setText("");
        textResultado.setText("");



    }
}




