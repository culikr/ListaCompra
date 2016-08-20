/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * @author LUIZ
 */
public class Produtos implements Serializable {
    private static final long serialVersionUID = -1;
    /**
     * Id do produto
     */
    private long id;

    /**
     * Nome do produto
     */
    private String nome;

    private long idMercado;
    private double preco;
    private String codbar;


    public Produtos(long id, String nome, long idMercado, double preco, String codbar) {
        this.id = id;
        this.nome = nome;
        this.idMercado = idMercado;
        this.preco = preco;
        this.codbar = codbar;
    }

    public Produtos(String nome, long idMercado, double preco, String codbar) {
        this.codbar = codbar;

        this.nome = nome;
        this.idMercado = idMercado;
        this.preco = preco;
    }

    public long getIdMercado() {

        return idMercado;
    }

    public void setIdMercado(long idMercado) {
        this.idMercado = idMercado;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Seta o codigo do produto
     *
     * @param id Codigo do produto
     */
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    /**
     * Seta o nome do produto
     *
     * @param nome Nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public Produtos() {
    }

    public Produtos(long id, String nome, double val, String codbar) {
        this.nome = nome;
        this.id = id;
        this.preco = val;
        this.codbar = codbar;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public void setCodbar(String codbar) {
        this.nome = codbar;
    }

    public String getCodbar() {
        return this.codbar;
    }

}
