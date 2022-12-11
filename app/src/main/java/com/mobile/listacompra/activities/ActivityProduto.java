package com.mobile.listacompra.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.listacompra.MainActivity;
import com.mobile.listacompra.R;
import com.mobile.listacompra.controller.ProdutoController;
import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

public class ActivityProduto extends AppCompatActivity {

    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtQuantidadeProduto;
    private EditText edtPrecoProduto;

    private Button btnSalvarProduto;

    private Button btnListarProdutos;


    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);

        edtNomeProduto = findViewById(R.id.edtNomeProduto);
        edtQuantidadeProduto = findViewById(R.id.edtQuantidadeProduto);
        edtPrecoProduto = findViewById(R.id.edtPrecoProduto);

        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        btnSalvarProduto = findViewById(R.id.btnSalvarProduto);

        this.clickBotaoSalvarProduto();

        this.clickBotaoListar();
    }

    private void clickBotaoSalvarProduto(){

        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Produto produtoACadastrar = getDadosProduto();

                if(produtoACadastrar != null) {
                    ProdutoController produtoController = new ProdutoController(ConectSQLite.getInstanciaConexao(ActivityProduto.this));
                    long idProduto = produtoController.salvarProdutoController(produtoACadastrar);
                    if(idProduto > 0){
                        Toast.makeText(ActivityProduto.this,"Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityProduto.this,"Produto não pode ser salvo!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActivityProduto.this,"Todos campos são obrigatorios", Toast.LENGTH_LONG).show();

                }


            }
        });

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
        if(edtQuantidadeProduto.getText().toString().isEmpty() == false) {
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

    private void clickBotaoListar(){
        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProduto.this, ListarProdutosActivity.class);
                startActivity(intent);

            }
        });
    }

}