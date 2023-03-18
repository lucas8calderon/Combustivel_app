package com.lucascalderon1.combustivel.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lucascalderon1.combustivel.adapter.AdapterProduto;
import com.lucascalderon1.combustivel.activity.FormProdutoActivity;
import com.lucascalderon1.combustivel.helper.Produto;
import com.lucascalderon1.combustivel.helper.ProdutoDAO;
import com.lucascalderon1.combustivel.R;
import com.lucascalderon1.combustivel.cards.AlcoolOuGasolinaActivity;
import com.lucascalderon1.combustivel.cards.DistanciaActivity;
import com.lucascalderon1.combustivel.cards.MediaActivity;
import com.lucascalderon1.combustivel.cards.PostoActivity;
import com.lucascalderon1.combustivel.cards.QuantoVouGastarActivity;
import com.lucascalderon1.combustivel.cards.RegistrosActivity;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterProduto.OnClick {

    private AdapterProduto adapterProduto;




    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;
    private FloatingActionButton fab_add;
    private ProdutoDAO produtoDAO;
    private ConstraintLayout layoutTransfer, layoutTransfer2, layoutTransfer3, layoutTransfer4, layoutTransfe5, layoutTransfer6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        produtoDAO = new ProdutoDAO(this);
        produtoList = produtoDAO.getListProdutos();


        layoutTransfer6 = findViewById(R.id.layoutTransfer6);
        layoutTransfer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro = new Intent(getApplicationContext(), RegistrosActivity.class);
                startActivity(intentRegistro);
            }
        });

        layoutTransfe5 = findViewById(R.id.layoutTransfe5);
        layoutTransfe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPosto = new Intent(getApplicationContext(), PostoActivity.class);
                startActivity(intentPosto);
            }
        });

        layoutTransfer4 = findViewById(R.id.layoutTransfer4);
        layoutTransfer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDistancia = new Intent(getApplicationContext(), DistanciaActivity.class);
                startActivity(intentDistancia);
            }
        });

        layoutTransfer3 = findViewById(R.id.layoutTransfe3);
        layoutTransfer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMedia = new Intent(getApplicationContext(), MediaActivity.class);
                startActivity(intentMedia);
            }
        });

        layoutTransfer2 = findViewById(R.id.layoutTransfer2);
        layoutTransfer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentQuanto = new Intent(getApplicationContext(), QuantoVouGastarActivity.class);
                startActivity(intentQuanto);
            }
        });

        layoutTransfer = findViewById(R.id.layoutTransfer);
        layoutTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGasolina = new Intent(getApplicationContext(), AlcoolOuGasolinaActivity.class);
                startActivity(intentGasolina);
            }
        });

        rvProdutos = findViewById(R.id.rvProdutos);
        configRecyclerView();

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRv = new Intent(getApplicationContext(), FormProdutoActivity.class);
                startActivity(intentRv);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        configRecyclerView();
    }


    private void configRecyclerView() {

        produtoList.clear();
        produtoList = produtoDAO.getListProdutos();
        Collections.reverse(produtoList);

        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);
        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {

                Produto produto = produtoList.get(position);

                produtoDAO.deleteProduto(produto);
                produtoList.remove(produto);
                adapterProduto.notifyItemRemoved(position);

            }
        });

    }


    @Override
    public void onClickListener(Produto produto) {

    }
}

