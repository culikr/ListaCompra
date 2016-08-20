package culik.br.com.listacompra.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LUIZ on 18/07/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "mercado"
})
public class LocalMercados implements Serializable {
    @JsonProperty("mercado")
    private List<Mercados> mercado = new ArrayList<>();

    @JsonProperty("mercado")
    public List<Mercados> getMercado() {
        return mercado;
    }

    @JsonProperty("mercado")
    public void setMercado(List<Mercados> mercados) {
        this.mercado = mercados;
    }

}
