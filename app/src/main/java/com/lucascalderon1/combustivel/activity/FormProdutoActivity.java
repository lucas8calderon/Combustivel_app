package com.lucascalderon1.combustivel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lucascalderon1.combustivel.helper.Produto;
import com.lucascalderon1.combustivel.helper.ProdutoDAO;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.cards.MascaraData;
import com.lucascalderon1.combustivel.cards.MascaraValorReal;
import com.lucascalderon1.combustivel.home.HomeActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edit_produto, edit_data, edit_valor;
    private ImageButton ib_voltar;

    private ProdutoDAO produtoDAO;
    private Produto produto;

    private final String FORMATO_VALOR_REAL = "R$ #,##0.00";
    private final DecimalFormat decimalFormat = new DecimalFormat(FORMATO_VALOR_REAL);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);





        ib_voltar = findViewById(R.id.ib_voltar);
        ib_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intentVoltar);
            }
        });


        produtoDAO = new ProdutoDAO(this);

        edit_produto = findViewById(R.id.edit_produto);
        edit_data = findViewById(R.id.edit_data);
        edit_valor = findViewById(R.id.edit_valor);

        edit_valor.addTextChangedListener(new MascaraValorReal(edit_valor));
        edit_data.addTextChangedListener(new DateTextWatcher(edit_data));


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            produto = (Produto)  bundle.getSerializable("produto");
            editProduto();

        }
    }

    private void editProduto(){
        edit_produto.setText(produto.getNome());
        edit_data.setText(String.valueOf(produto.getData()));
        edit_valor.setText(String.valueOf(produto.getValor()));

    }




    public void salvarProduto(View view) {
        String nome = edit_produto.getText().toString();
        String data = edit_data.getText().toString();
        String valor = edit_valor.getText().toString().replaceAll("[^0-9.,]+", "");

        if (!nome.isEmpty()) {
            if (!data.isEmpty()) {
                String dataSemMascara = MascaraData.unmask(data); // Remove a máscara de data
                int qntd = 0;
                try {
                    qntd = Integer.parseInt(dataSemMascara); // Converte a String sem máscara em um inteiro
                } catch (NumberFormatException e) {
                    // Trata a exceção, se necessário
                }
                if (qntd >= 1) {
                    if (!valor.isEmpty()) {
                        double valorProduto = Double.parseDouble(valor.replace(",", "."));
                        if (valorProduto > 0) {
                            if (produto == null) produto = new Produto();
                            produto.setNome(nome);
                            try {
                                Date date = dateFormat.parse(data);
                                produto.setData(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            produto.setValor(valorProduto);

                            if (produto.getId() != 0) {
                                produtoDAO.atualizaProduto(produto);
                                Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            } else {
                                produtoDAO.salvarProduto(produto);
                                Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }
                    } else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe um valor válido.");
                    }
                } else {
                    edit_data.requestFocus();
                    edit_data.setText("");
                    edit_data.setError("Informe uma data válida.");
                }
            } else {
                edit_data.requestFocus();
                edit_data.setError("Informe um valor válido.");
            }
        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do combustivel");
        }
    }



}