package com.weiler.itinerarydimed.repositorys;

import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

//    @Query(value=
//            "SELECT * " +
//                    "FROM spot s " +
//                    "WHERE line_id = :lineId " +
//                    "and st_distance_sphere(ST_MakePoint(s.lng, s.lat),ST_MakePoint(:lng,:lat)) <= :radiusInMeters",
//            nativeQuery = true)
//    List<Itinerary> findByLineAndSpotInRadius(@Param("lat") Double lat, @Param("lng") Double lng, @Param("lineId") Long lineId, @Param("radiusInMeters") Long radiusInMeters);

    @Query("SELECT p FROM Itinerary p WHERE p.bus.id = :idLinha AND p.indice = :indice")
    public Itinerary findByIndiceAndLinha(@Param("indice") Long indice, @Param("idLinha") Long idLinha);

    Itinerary findItinerarById (Long id);


}
