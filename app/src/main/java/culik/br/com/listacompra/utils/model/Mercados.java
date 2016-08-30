package culik.br.com.listacompra.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created by LUIZ on 18/07/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "id",
        "lat",
        "lgn",
        "nome"
})
public class Mercados implements Serializable {
    private static final long serialVersionUID = -1;
    @JsonProperty("id")
    private long id;

    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lgn")
    private double lgn;
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("lat")
    public double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(double lat) {
        this.lat = lat;
    }

    @JsonProperty("lgn")
    public double getLgn() {
        return lgn;
    }

    @JsonProperty("lgn")
    public void setLgn(double lgn) {
        this.lgn = lgn;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Mercados(long id, double lat, double lgn, String nome) {
        this.id = id;
        this.lat = lat;
        this.lgn = lgn;
        this.nome = nome;
    }

    public Mercados( double lat, double lgn, String nome) {

        this.lat = lat;
        this.lgn = lgn;
        this.nome = nome;
    }

    public Mercados() {
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
