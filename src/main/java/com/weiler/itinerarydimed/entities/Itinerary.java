package com.weiler.itinerarydimed.entities;

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
    @SequenceGenerator(name = "seqpontotransporte", sequenceName = "seqpontotransporte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqpontotransporte")
    private Long id;
    private Long indice;
    private Double lat;
    private Double lng;
    @ManyToOne
    @JoinColumn(name="BUS_ID",referencedColumnName = "id")
    private Bus bus;

//
//    public Itinerary(String idBus, String latitude, String longitude) {
//    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

//
//    public boolean fullEquals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Itinerary other = (Itinerary) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        if (lat == null) {
//            if (other.lat != null)
//                return false;
//        } else if (!lat.equals(other.lat))
//            return false;
//        if (lng == null) {
//            if (other.lng != null)
//                return false;
//        } else if (!lng.equals(other.lng))
//            return false;
//        return true;
//    }

}