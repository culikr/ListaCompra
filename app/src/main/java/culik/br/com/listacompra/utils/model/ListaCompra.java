package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class  ListaCompra implements Serializable {
    private static final long serialVersionUID = -1;
    /**
     * Nome do produto
     */
    private String sNome;
    /**
     * Id do produto
     */
    private long idLista;

    private String dDataCad;

    private String sEmail;
    private String sTelefone;

    public long getIdMercado() {
        return idMercado;
    }

    public void setIdMercado(long idMercado) {
        this.idMercado = idMercado;
    }

    private long idMercado;

    public String getsMensagem() {
        return sMensagem;
    }

    public void setsMensagem(String sMensagem) {
        this.sMensagem = sMensagem;
    }

    private String sMensagem;

    public String getsTelefone() {
        return sTelefone;
    }

    public void setsTelefone(String sTelefone) {
        this.sTelefone = sTelefone;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getdDataCad() {
        return dDataCad;
    }

    public void setdDataCad(String dDataCad) {
        this.dDataCad = dDataCad;
    }

    public ListaCompra(long idLista, String sNome, String dDataCad, String email, String telefone, String mensagem) {
        this.sNome = sNome;
        this.idLista = idLista;
        this.dDataCad = dDataCad;
        this.sEmail = email;
        this.sTelefone=telefone;
        this.sMensagem=mensagem;

    }
    public ListaCompra(long idLista, String sNome, String dDataCad, String email, String telefone, String mensagem,long idmercado) {
        this.sNome = sNome;
        this.idLista = idLista;
        this.dDataCad = dDataCad;
        this.sEmail = email;
        this.sTelefone=telefone;
        this.sMensagem=mensagem;
        this.idMercado=idmercado;

    }
    public ListaCompra( String sNome, String dDataCad, String email, String telefone,String mensagem) {
        this.sNome = sNome;
        this.dDataCad = dDataCad;
        this.sEmail = email;
        this.sTelefone=telefone;
        this.sMensagem = mensagem;
    }
    public ListaCompra( String sNome, String dDataCad, String email, String telefone,String mensagem,long idmercado) {
        this.sNome = sNome;
        this.dDataCad = dDataCad;
        this.sEmail = email;
        this.sTelefone=telefone;
        this.sMensagem = mensagem;
        this.idMercado=idmercado;

    }

    public void setIdLista( long id ){
        this.idLista= id;
    }
    public long getIdLista(){
        return this.idLista;
    }

    public void setsNome( String nome ){
        this.sNome= nome;
    }

    public String getsNome(){
        return this.sNome;
    }

}
