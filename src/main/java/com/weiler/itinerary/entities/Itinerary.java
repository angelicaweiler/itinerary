package com.weiler.itinerary.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Itinerary {

    @Id
    @SequenceGenerator(name = "seqitinirary", sequenceName = "seqitinirary", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqitinirary")
    private Long id;
    private Long indice;
    private Double lat;
    private Double lng;
    @ManyToOne
    @JoinColumn(name="BUS_ID",referencedColumnName = "id")
    private Bus bus;

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}