package com.mobile.listacompra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.mobile.listacompra.R;
import com.mobile.listacompra.adapters.AdapterListaProdutos;
import com.mobile.listacompra.controller.ProdutoController;
import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        //TODO buscar produtos no banco

        ProdutoController produtoController = new ProdutoController(ConectSQLite.getInstanciaConexao(ListarProdutosActivity.this));
        produtoList = produtoController.getListaProdutosController();

        this.lsvProdutos = (ListView) findViewById(R.id.lsvProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ListarProdutosActivity.this, this.produtoList);

        this.lsvProdutos.setAdapter(this.adapterListaProdutos);

    }
}