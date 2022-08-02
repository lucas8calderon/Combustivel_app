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

public class DistanciaActivity extends AppCompatActivity {

    private ImageButton ib_voltar;
    private TextInputEditText editDistancia, editLitros;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia);

        textResultado = findViewById(R.id.textResultado);
        ib_voltar = findViewById(R.id.ib_voltar);
        editDistancia = findViewById(R.id.editDistancia);
        editLitros = findViewById(R.id.editLitros);
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
        dialog.setMessage(
                "1 - No primeiro campo digite o rendimento que seu veiculo faz por litro de combustivel.\n" +
                        "2 - No segundo campo digite o total de litros abastecidos \n" +
                        "3 - Ao final clique em 'CALCULAR' assim tendo o retorno da distância que seu veiculo percorrerá.");

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

        Double resultado = valorAlcool * valorGasolina;

        textResultado.setText("Seu veiculo percorrerá " + String.valueOf(resultado) + "km");


    }

    public void limpar(View view) {
        editDistancia.setText("");
        editLitros.setText("");

    }
}





