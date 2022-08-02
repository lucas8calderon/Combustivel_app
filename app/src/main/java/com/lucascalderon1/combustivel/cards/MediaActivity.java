package com.lucascalderon1.combustivel.cards;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.home.HomeActivity;

public class MediaActivity extends AppCompatActivity {

    private ImageButton ib_voltar;
    private TextInputEditText editDistancia, editLitros;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        //validarCampos();

        textResultado = findViewById(R.id.textResultado);

        ib_voltar = findViewById(R.id.ib_voltar);
        editDistancia = findViewById(R.id.editDistancia);
        editLitros = findViewById(R.id.editLitros);
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
        dialog.setMessage(
                "1 - Com o carro na reserva você deve encher o tanque completamente e preencher o valor abastecido no primeiro campo.\n" +
                        "2 - Agora rode até chegar na reserva novamente e preencha a quantidade de Km qye rodou no segundo campo. \n" +
                        "3 - No terceiro, digite o valor do preço do combustivel por litro.  \n" +
                        "4 - Ao final clique em 'CALCULAR' assim tendo o retorno de quanto o seu veiculo rende por litro de combustivel.");

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

        String distanciaPercorrida = editDistancia.getText().toString();
        String litrosAbastecidos = editLitros.getText().toString();


        Double valorAlcool = Double.parseDouble(distanciaPercorrida);
        Double valorGasolina = Double.parseDouble(litrosAbastecidos);

        Double resultado = valorAlcool / valorGasolina;


        textResultado.setText("Seu veiculo está fazendo " + String.valueOf(resultado) + "km por litro de combustivel.");


    }

    public void limpar(View view) {

        editLitros.setText("");
        editDistancia.setText("");

    }
}

