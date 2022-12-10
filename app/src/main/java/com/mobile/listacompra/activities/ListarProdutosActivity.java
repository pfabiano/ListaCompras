package com.mobile.listacompra.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(posicao);
                Toast.makeText(ListarProdutosActivity.this, "Produto: "+ produtoSelecionado.getNome(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ListarProdutosActivity.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com o produto selecionado?");

                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        boolean excluiu = produtoController.excluirProdutoController(produtoSelecionado.getId());
                        dialogInterface.cancel();

                        if(excluiu == true){
                            Toast.makeText(ListarProdutosActivity.this, "Produto excluido com sucesso", Toast.LENGTH_SHORT);
                        }else{
                            Toast.makeText(ListarProdutosActivity.this, "Erro ao excluido produto", Toast.LENGTH_SHORT);
                        }



                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });



                janelaDeEscolha.create().show();



            }
        });

    }
}