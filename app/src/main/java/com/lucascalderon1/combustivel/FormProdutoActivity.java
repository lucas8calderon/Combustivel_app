package com.lucascalderon1.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lucascalderon1.combustivel.home.HomeActivity;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edit_produto, edit_data, edit_valor;
    private ImageButton ib_voltar;

    private ProdutoDAO produtoDAO;
    private  Produto produto;

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


    public void salvarProduto (View view) {

        String nome = edit_produto.getText().toString();
        String data = edit_data.getText().toString();
        String valor = edit_valor.getText().toString();

        if (!nome.isEmpty()){

            if (!data.isEmpty()){

                int qntd = Integer.parseInt(data);

                if (qntd >= 1) {

                    if (!valor.isEmpty()) {

                        double valorProduto = Double.parseDouble(valor);

                        if (valorProduto > 0) {
                           if (produto == null) produto = new Produto();
                           produto.setNome(nome);
                           produto.setData(qntd);
                           produto.setValor(valorProduto);

                           if (produto.getId() != 0) {
                               produtoDAO.atualizaProduto(produto);
                           } else {
                               produtoDAO.salvarProduto(produto);
                           }
                           finish();
                        }

                    } else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe um valor válido.");

                }

            } else {
                    edit_data.requestFocus();
                    edit_data.setError("Informe uma data válida.");

                }




            } else {
                edit_data.requestFocus();
                edit_data.setError("Informe um valor válido.");
            }

        }else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do combustivel");
        }




    }


}