package com.lucascalderon1.combustivel.cards;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.home.HomeActivity;

import java.util.Currency;

public class AlcoolOuGasolinaActivity extends AppCompatActivity {
    private ImageButton ib_voltar, ib_duvida;
    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado, textView4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_ou_gasolina);

        textView4 = findViewById(R.id.textView4);
        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);






        ib_voltar = findViewById(R.id.ib_voltar);
        ib_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                finish();
                startActivity(intent);
            }
        });


    }

    public void abrirDialog(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(" Instruções");
        dialog.setMessage("1 - No primeiro campo digite o valor atual do álcool. \n" +
                "2 - No segundo digite o valor atual da gasolina. \n" +
                "3 - Ao final clique em 'CALCULAR' assim tendo retorno de qual combustível é mais vantajoso abastecer. ");

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

        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);
        if (camposValidados) {

            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);

            Double resultado = valorAlcool / valorGasolina;
            if (resultado >= 0.7 ) {
                textResultado.setText("Melhor utilizar gasolina.");
                textView4.setText("Resultado:");
            } else {
                textResultado.setText("Melhor utilizar álcool.");
                textView4.setText("Resultado:");
            }

        } else {
            textResultado.setText("Preencha todos os campos primeiro.");
        }


    }

    public Boolean validarCampos (String pAlcool, String pGasolina) {

        Boolean camposValidados = true;



        if ( pAlcool == null || pAlcool.equals("")) {
            camposValidados = false;

        } else if (pGasolina == null || pGasolina.equals("")) {

        }

        return camposValidados;

    }

    public void limpar(View view) {
        editPrecoGasolina.setText("");
        editPrecoAlcool.setText("");
        textResultado.setText("");
        textView4.setText("");

    }
}