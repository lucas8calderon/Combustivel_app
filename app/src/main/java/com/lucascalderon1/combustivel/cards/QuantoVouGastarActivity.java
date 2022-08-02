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

public class QuantoVouGastarActivity extends AppCompatActivity {

    private TextInputEditText editDistancia, editLitros, editRendimento, editValor;
    private TextView textResultado;
    private ImageButton ib_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanto_vou_gastar);

        editDistancia = findViewById(R.id.editDistancia);
        editValor = findViewById(R.id.editValor);
        textResultado = findViewById(R.id.textResultado);
        editLitros = findViewById(R.id.editLitros);
        editRendimento = findViewById(R.id.editRendimento);


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
                "1 - No primeiro campo, digite a distância a percorer. \n" +
                "2 - No segundo, digite o rendimento por litro. \n" +
                "3 - No terceiro, digite o valor do preço do combustivel por litro.  \n" +
                "3 - Ao final clique em 'CALCULAR' assim tendo retorno estimado que irá gastar em seu percurso ou viajem. ");

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
        String litrosAbastecidos = editRendimento.getText().toString();
        String litrosValor = editValor.getText().toString();


        Double valorAlcool = Double.parseDouble(distanciaPercorrida);
        Double valorGasolina = Double.parseDouble(litrosAbastecidos);
        Double valorLitros = Double.parseDouble(litrosValor);

        Double resultado = valorAlcool / valorGasolina * valorLitros;
        Double litros = valorAlcool / valorGasolina;




        textResultado.setText("Você precisara de "+ litros + " litros e gastará aproximadamente R$" + String.valueOf(resultado));




    }




    public void limpar(View view) {
        editDistancia.setText("");
        editRendimento.setText("");
        editValor.setText("");

    }
}




