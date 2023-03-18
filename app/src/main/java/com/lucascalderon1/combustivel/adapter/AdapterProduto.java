package com.lucascalderon1.combustivel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucascalderon1.combustivel.helper.Produto;
import com.lucascalderon1.combustivel.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> produtoList;
    private OnClick onClick;

    public AdapterProduto(List<Produto> produtoList, OnClick onClick) {
        this.produtoList = produtoList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.textProduto.setText(produto.getNome());

        if (produto.getData() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = dateFormat.format(produto.getData());
            holder.textData.setText("Data: " + dataFormatada);
        } else {
            holder.textData.setText("Data: -");
        }

        holder.textValor.setText("R$ " + produto.getValor());

        holder.itemView.setOnClickListener(v -> onClick.onClickListener(produto));
    }


    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public interface OnClick {
        void onClickListener(Produto produto);
    }

    static class MyViewHolder extends  RecyclerView.ViewHolder{


        TextView textProduto, textData, textValor;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textProduto = itemView.findViewById(R.id.text_produto);
            textData = itemView.findViewById(R.id.text_data);
            textValor = itemView.findViewById(R.id.text_valor);
        }
    }

}
