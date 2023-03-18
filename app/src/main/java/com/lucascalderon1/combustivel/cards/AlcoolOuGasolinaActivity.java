package com.lucascalderon1.combustivel.cards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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


public class AlcoolOuGasolinaActivity extends AppCompatActivity {


    private AdView adView;
    private ImageButton ib_voltar, ib_resultado;
    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado, textView4;



    private final String FORMATO_VALOR_REAL = "R$ #,##0.00";
    private final DecimalFormat decimalFormat = new DecimalFormat(FORMATO_VALOR_REAL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina);

        MobileAds.initialize(this, initializationStatus -> {

        });

        adView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        textView4 = findViewById(R.id.textView4);
        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);
        ib_resultado = findViewById(R.id.ib_resultado);
        editPrecoAlcool.addTextChangedListener(new MascaraValorReal(editPrecoAlcool));
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
                "1 - No primeiro campo digite o valor atual do álcool.\n" +
                        "2 - No segundo, digite o valor atual da gasolina.\n" +
                        "3 - Ao final clique em CALCULAR, assim tendo retorno de qual combustível é mais vantajoso abastecer.\n" +
                        "4 - Cálculo baseado na agência nacional de petróleo, onde só compensa abastecer com álcool quando o valor do mesmo represente no máximo 70% do valor da gasolina.");

        dialog.setIcon(R.drawable.ic_help);


        dialog.setNegativeButton("OK", (dialog1, which) -> Toast.makeText(getApplicationContext(), "Instruções minimizadas", Toast.LENGTH_SHORT).show());

        dialog.create();
        dialog.show();

    }

    public void calcularPreco(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);



        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);
        if (camposValidados) {

            Double valorAlcool = MascaraValorReal.parseDouble(MascaraValorReal.unmask(editPrecoAlcool.getText().toString()));
            Double valorGasolina = MascaraValorReal.parseDouble(MascaraValorReal.unmask(editPrecoGasolina.getText().toString()));


            Double resultado = valorAlcool / valorGasolina;
            if (resultado >= 0.7) {
                textResultado.setText("Melhor utilizar gasolina");
                ib_resultado.setImageResource(R.drawable.ic_drink2);

            } else {
                textResultado.setText("Melhor utilizar álcool");
                ib_resultado.setImageResource(R.drawable.ic_drink);

            }
            textView4.setText("Resultado:");

        } else {
            textResultado.setText("Preencha todos os campos primeiro.");
        }



    }

    public Boolean validarCampos(String pAlcool, String pGasolina) {

        Boolean camposValidados = true;


        if (pAlcool == null || pAlcool.equals("")) {
            camposValidados = false;

        } else if (pGasolina == null || pGasolina.equals("")) {

        }

        return camposValidados;

    }

    public void limpar(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        editPrecoGasolina.setText("");
        editPrecoAlcool.setText("");
        textResultado.setText("");
        textView4.setText("");

    }

}