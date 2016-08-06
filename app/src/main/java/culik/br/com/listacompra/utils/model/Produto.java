package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class Produto implements Serializable {
    private static final long serialVersionUID = -1;
    /**
     * Id do produto
     */
    private long idProduto;

    /**
     * Nome do produto
     */
    private String sNome;

    private String sLocal;
    private double preco;

    public Produto(long idProduto, String sNome, String sLocal, double preco) {
        this.idProduto = idProduto;
        this.sNome = sNome;
        this.sLocal = sLocal;
        this.preco = preco;
    }

    public Produto( String sNome, String sLocal, double preco) {

        this.sNome = sNome;
        this.sLocal = sLocal;
        this.preco = preco;
    }

    public String getsLocal() {

        return sLocal;
    }

    public void setsLocal(String sLocal) {
        this.sLocal = sLocal;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Seta o codigo do produto
     * @param id Codigo do produto
     */
    public void setIdProduto( long id ){
        this.idProduto= id;
    }
    public long getIdProduto(){
        return this.idProduto;
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

    public Produto() {}
    public Produto( long id, String nome,double val ){
        this.sNome= nome;
        this.idProduto= id;
        this.preco=val;
    }
    @Override
    public String toString()
    {
        return this.sNome;
    }
}
