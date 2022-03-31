package com.weiler.itinerarydimed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDto implements Serializable {

    private String idlinha;
    private String codigo;
    private String nome;
    private List<BusDto> pontos = new ArrayList<>();

    @Override
    public String toString() {
        return "ItineraryD{" + "idlinha=" + idlinha + ", codigo=" + codigo + ", nome=" + nome + ", pontos=" + pontos + '}';
    }

}
