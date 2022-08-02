package com.lucascalderon1.combustivel.cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.lucascalderon1.combustivel.AdapterProduto;
import com.lucascalderon1.combustivel.FormProdutoActivity;
import com.lucascalderon1.combustivel.Produto;
import com.lucascalderon1.combustivel.ProdutoDAO;
import com.lucascalderon1.combustivel.R;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class RegistrosActivity extends AppCompatActivity implements AdapterProduto.OnClick {
    private AdapterProduto adapterProduto;
    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;
    private TextView text_info;
    private ImageButton ibAdd, ibVerMais;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        produtoDAO = new ProdutoDAO(this);
        produtoList = produtoDAO.getListProdutos();
        text_info = findViewById(R.id.text_info);
        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);





        rvProdutos = findViewById(R.id.rvProdutos);
        configRecyclerView();
        ouvinteCliques();
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

        ibVerMais.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, ibVerMais);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());


            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menu_sobre){
                    Toast.makeText(this, "Sobre", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popupMenu.show();
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