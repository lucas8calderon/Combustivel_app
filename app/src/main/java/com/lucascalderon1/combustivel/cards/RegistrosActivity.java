package com.lucascalderon1.combustivel.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.lucascalderon1.combustivel.adapter.AdapterProduto;
import com.lucascalderon1.combustivel.activity.FormProdutoActivity;
import com.lucascalderon1.combustivel.helper.Produto;
import com.lucascalderon1.combustivel.helper.ProdutoDAO;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.home.HomeActivity;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RegistrosActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdView adView;
    private AdapterProduto adapterProduto;
    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;
    private TextView text_info;
    private ImageButton ibAdd, ib_voltar;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        MobileAds.initialize(this, initializationStatus -> {

        });

        adView= findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        produtoDAO = new ProdutoDAO(this);
        produtoList = produtoDAO.getListProdutos();
        text_info = findViewById(R.id.text_info);
        ibAdd = findViewById(R.id.ib_add);
        ib_voltar = findViewById(R.id.ib_voltar);






        rvProdutos = findViewById(R.id.rvProdutos);
        configRecyclerView();
        ouvinteCliques();

        ib_voltar = findViewById(R.id.ib_voltar);
        ib_voltar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            finish();
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecyclerView();
    }

    private void ouvinteCliques(){
        ibAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, FormProdutoActivity.class));
        });





    }




    private void configRecyclerView(){

        produtoList.clear();
        produtoList = produtoDAO.getListProdutos();
        verificaQtdLista();
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);
        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                Toast.makeText(RegistrosActivity.this, "ainda não é possível editar, atualize a pagina.", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onSwipedRight(int position) {

                Produto produto = produtoList.get(position);

                produtoDAO.deleteProduto(produto);
                produtoList.remove(produto);
                adapterProduto.notifyItemRemoved(position);

                verificaQtdLista();


            }
        });

    }

    private void verificaQtdLista() {

        if (produtoList.size() == 0) {
            text_info.setVisibility(View.VISIBLE);

        } else {
            text_info.setVisibility(View.GONE);
        }

    }



    @Override
    public void onClickListener(Produto produto) {
      Intent intent = new Intent(this, FormProdutoActivity.class);
      intent.putExtra("produto", produto);
      startActivity(intent);
    }


}