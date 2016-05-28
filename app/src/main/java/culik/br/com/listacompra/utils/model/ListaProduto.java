package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class ListaProduto implements Serializable {
    /**
     * Id da lista de compra
     */
    private long idLista;
    /**
     * Id do produto
     */
    private long idProduto;

    /**
     * Id do item da lista
     */
    private long idItem ;

    /**
     * Quantidade
     */
    private double dQuant;

    /**
     * Nome do produto
     */
    private String sNome;
    /**
     * seta o codigo da lista de compra
     * @param id codigo da lista
     */
    public void  setIdLista( long id ){
        this.idLista = id;
    }
    /**
     * retorna o codigo da lista de compra
     * @return codigo da lista
     */
    public long getIdLista(){
        return this.idLista;
    }

    /**
     * seta o codigo do produto
     * @param id codigo do produto
     */
    public void setIdProduto( long id ){
        this.idProduto= id;
    }

    /**
     * retorna o codigo do produto
     * @return codigo do produto
     */
    public long getIdProduto(){
        return this.idProduto;
    }

    /**
     * seta o codigo do item da lista de compra
     * @param id codigo do produto na lista de compra
     */
    public void setIdItem( long id ){
        this.idItem= id;
    }


    /**
     * retorna o codigo do item na lista de compra
     * @return codigo do produto
     */
    public long getIdItem(){
        return this.idItem;
    }

    /**
     * seta a quantida de produto na lista de compra
     * @param quant quantidade de produto
     */
    public void setdQuant( double quant ){
        this.dQuant= quant;
    }

    /**
     * retorna a quantidade de produto da lista de compra
     * @return quantidade de produto
     */
    public double getdQuant(){
        return this.dQuant;
    }

    /**
     * Seta o nome do produto
     * @param nome Nome do produto
     */public void setsNome( String nome ){
        this.sNome= nome;
    }

    public String getsNome(){
        return this.sNome;
    }

    public ListaProduto(){}

    public ListaProduto(long idLista,long idProd, long idItem, double dQuant,String nome ){
        this.idItem=idItem;
        this.idProduto=idProd;
        this.idLista=idLista;
        this.dQuant=dQuant;
        this.sNome =nome;
    }

    public ListaProduto(long idProd, long idLista, double dQuant,String nome ){

        this.idProduto=idProd;
        this.idLista=idLista;
        this.dQuant=dQuant;
        this.sNome =nome;
    }


}
