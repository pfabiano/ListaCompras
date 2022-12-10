package com.mobile.listacompra.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO<listaProdutos> {

    private final ConectSQLite conexaoSQLite;

    public ProdutoDAO(ConectSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarProdutoDAO(Produto pProduto){

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("id", pProduto.getId());
            values.put("nome", pProduto.getNome());
            values.put("quantidade_em_estoque", pProduto.getQuantidade());
            values.put("preco", pProduto.getPreco());

            long idProdutoInserido = db.insert("produto", null, values);

            return idProdutoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(db !=null){
                db.close();
            }
        }
        return 0;
    }

    public List<Produto> getListaProdutosDAO(){

        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produto;";

        try{
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()) {

                Produto produtoTemporario = null;

                do {
                    produtoTemporario = new Produto();
                    produtoTemporario.setId(cursor.getLong(0));
                    produtoTemporario.setNome(cursor.getString(1));
                    produtoTemporario.setQuantidade(cursor.getInt(2));
                    produtoTemporario.setPreco(cursor.getDouble(3));

                    listaProdutos.add(produtoTemporario);

                } while (cursor.moveToNext());
            }
        }catch (Exception e){
                Log.d("ERRO LISTA PROUTOS", "Erro ao retornar os produtos;");
                return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaProdutos;
    }

}
