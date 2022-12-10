package com.mobile.listacompra.model;

import java.util.Date;

public class Venda {

    private  long id;
    private Produto produtoVendido;
    private Date dataVenda;
    private String nomeCliente;

    public Venda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", produtoVendido=" + produtoVendido.toString() +
                ", dataVenda=" + dataVenda +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
