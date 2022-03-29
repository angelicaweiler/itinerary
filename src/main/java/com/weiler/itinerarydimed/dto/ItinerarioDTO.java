package com.weiler.itinerarydimed.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gianlucampos
 */
public class ItinerarioDTO implements Serializable {

    private String idlinha;
    private String codigo;
    private String nome;
    private List<BusDto> pontos = new ArrayList<>();

    public ItinerarioDTO() {
    }

    public ItinerarioDTO(String idlinha, String codigo, String nome, List<BusDto> pontos) {
        this.idlinha = idlinha;
        this.codigo = codigo;
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getIdlinha() {
        return idlinha;
    }

    public void setIdlinha(String idlinha) {
        this.idlinha = idlinha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<BusDto> getPontos() {
        return pontos;
    }

    public void setPontos(List<BusDto> pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "ItinerarioDTO{" + "idlinha=" + idlinha + ", codigo=" + codigo + ", nome=" + nome + ", pontos=" + pontos + '}';
    }

}
