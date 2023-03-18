package com.lucascalderon1.combustivel.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProdutoDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public ProdutoDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarProduto(Produto produto) {
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("data", dateFormat.format(produto.getData()));
        cv.put("valor", produto.getValor());

        try {
            write.insert(DBHelper.TB_PRODUTO, null, cv);
        } catch (Exception e) {
            Log.i("Error", "Erro ao salvar Registro: " + e.getMessage());
        }
    }

    @SuppressLint("Range")
    public List<Produto> getListProdutos() {
        List<Produto> produtoList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TB_PRODUTO + ";";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String nome = c.getString(c.getColumnIndex("nome"));
            Date data = null;
            try {
                data = dateFormat.parse(c.getString(c.getColumnIndex("data")));
            } catch (ParseException e) {
                Log.i("Error", "Erro ao converter data: " + e.getMessage());
            }
            double valor = c.getDouble(c.getColumnIndex("valor"));

            Produto produto = new Produto();
            produto.setId(id);
            produto.setNome(nome);
            produto.setData(data);
            produto.setValor(valor);

            produtoList.add(produto);
        }

        return produtoList;
    }

    public void atualizaProduto(Produto produto) {
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("data", dateFormat.format(produto.getData()));
        cv.put("valor", produto.getValor());

        String where = "id=?";
        String[] args = {String.valueOf(produto.getId())};

        try {
            write.update(DBHelper.TB_PRODUTO, cv, where, args);
        } catch (Exception e) {
            Log.i("Error", "erro ao atualizar produto " + e.getMessage());
        }
    }

    public void deleteProduto(Produto produto) {
        try {
            String[] args = {String.valueOf(produto.getId())};
            write.delete(DBHelper.TB_PRODUTO, "id=?", args);
        } catch (Exception e) {
            Log.i("ERROR", "Erro ao deletar produto: " + e.getMessage());
        }
    }
}
