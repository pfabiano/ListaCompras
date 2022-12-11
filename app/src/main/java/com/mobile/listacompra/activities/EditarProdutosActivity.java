 package com.mobile.listacompra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.listacompra.MainActivity;
import com.mobile.listacompra.R;
import com.mobile.listacompra.controller.ProdutoController;
import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

 public class EditarProdutosActivity extends AppCompatActivity {

     private EditText edtIdProduto;
     private EditText edtNomeProduto;
     private EditText edtPrecoProduto;
     private EditText edtQuantidadeProduto;

     private Button btnSalvarAlteracoes;

     private Button btnListarProdutos;

     private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produtos);

        this.edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        this.edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        this.edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        this.edtQuantidadeProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);

        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        this.btnSalvarAlteracoes = (Button) findViewById(R.id.btnSalvarAlteracoes);

        Bundle bundleDadosProduto = getIntent().getExtras();

        Produto produto = new Produto();

        produto.setId(bundleDadosProduto.getLong("id_produto"));
        produto.setNome(bundleDadosProduto.getString("nome_produto"));
        produto.setPreco(bundleDadosProduto.getDouble("preco_produto"));
        produto.setQuantidade(bundleDadosProduto.getInt("estoque_produto"));

        this.setDadosProduto(produto);

        this.clickBotaoSalvarProduto();

        this.clickBotaoListar();

    }

    private void setDadosProduto(Produto produto){

        this.edtIdProduto.setText(String.valueOf(produto.getId()));
        this.edtNomeProduto.setText(produto.getNome());
        this.edtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        this.edtQuantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
    }

    private Produto getDadosProduto(){
        this.produto = new Produto();

        if(this.edtIdProduto.getText().toString().isEmpty() == false){
             this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
        }else {
            return null;
        }

        if(this.edtNomeProduto.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edtNomeProduto.getText().toString());
        }else
            return null;
        if(this.edtQuantidadeProduto.getText().toString().isEmpty() == false) {
            int quantidadeProduto = Integer.parseInt(this.edtQuantidadeProduto.getText().toString());
            this.produto.setQuantidade(quantidadeProduto);
        }else
            return null;
        if(edtPrecoProduto.getText().toString().isEmpty() == false) {
             double precoProduto = Double.parseDouble(this.edtPrecoProduto.getText().toString());
             this.produto.setPreco(precoProduto);
        }else
            return null;

        return produto;
    }
    private void clickBotaoSalvarProduto(){

        this.btnSalvarAlteracoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Produto produtoAAtualizar = getDadosProduto();

                if(produtoAAtualizar != null) {

                    ProdutoController produtoController = new ProdutoController(ConectSQLite.getInstanciaConexao(EditarProdutosActivity.this));
                    boolean atualizou = produtoController.atualizarProdutoController(produtoAAtualizar);
                    if(atualizou == true){
                        Toast.makeText(EditarProdutosActivity.this,"Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(EditarProdutosActivity.this,"Produto não pode ser salvo!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(EditarProdutosActivity.this,"Todos campos são obrigatorios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void clickBotaoListar(){
        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarProdutosActivity.this, ListarProdutosActivity.class);
                startActivity(intent);
            }
        });
     }

}