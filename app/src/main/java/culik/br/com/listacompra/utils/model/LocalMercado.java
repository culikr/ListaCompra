package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * Created by LUIZ on 18/07/2016.
 */
public class LocalMercado implements Serializable {
    private static final long serialVersionUID = -1;
    private long id;


    private double lat;
    private double lgn;
    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLgn() {
        return lgn;
    }

    public void setLgn(double lgn) {
        this.lgn = lgn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalMercado(long id, double lat, double lgn, String nome) {
        this.id = id;
        this.lat = lat;
        this.lgn = lgn;
        this.nome = nome;
    }

}
