/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culik.br.com.listacompra.utils.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author LUIZ
 */
public class ListaProdutos implements Serializable {
    private ArrayList<Produtos> produto = new ArrayList<>();

    public ArrayList<Produtos> getProduto() {
        return produto;
    }

    public void setProduto(ArrayList<Produtos> produtos) {
        this.produto = produtos;
    }
}
