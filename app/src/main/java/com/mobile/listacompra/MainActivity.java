package com.mobile.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobile.listacompra.activities.ActivityProduto;
import com.mobile.listacompra.activities.ListarProdutosActivity;
import com.mobile.listacompra.controller.ProdutoController;
import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroProdutos;
    private Button btnListarProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConectSQLite conexaoSQLITE = ConectSQLite.getInstanciaConexao(this);



        this.btnCadastroProdutos = (Button) findViewById(R.id.btnCadastroProdutos);
        
        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        this.btnCadastroProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);
            }
        });

        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                startActivity(intent);
            }
        });
    }
}