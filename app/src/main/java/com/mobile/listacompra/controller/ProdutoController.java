package com.mobile.listacompra.controller;

import com.mobile.listacompra.DAO.ProdutoDAO;
import com.mobile.listacompra.dbHelper.ConectSQLite;
import com.mobile.listacompra.model.Produto;

import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;

    public ProdutoController(ConectSQLite pConexaoSQLite){
        produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }

    public long salvarProdutoController (Produto pProduto){
        return this.produtoDAO.salvarProdutoDAO(pProduto);
    }

    public List<Produto> getListaProdutosController(){
        return this.produtoDAO.getListaProdutosDAO();
    }

    public boolean excluirProdutoController(long pIdProduto){
        return this.produtoDAO.excluirProdutoDAO(pIdProduto);
    }

    public boolean atualizarProdutoController(Produto pProduto){
        return this.produtoDAO.atualizarProdutoDAO(pProduto);
    }

}
