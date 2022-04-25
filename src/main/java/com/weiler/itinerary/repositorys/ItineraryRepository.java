package com.weiler.itinerary.repositorys;

import com.weiler.itinerary.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    Itinerary findItineraryById(Long id);

    @Query("SELECT p FROM Itinerary p WHERE p.bus.id = :idLinha AND p.indice = :indice")
    public Itinerary findByIndiceAndLinha(@Param("indice") Long indice, @Param("idLinha") Long idLinha);

//    @Query(value=
//            "SELECT * " +
//                    "FROM bus b " +
//                    "INNER JOIN itinerary i " +
//                    "and st_distance_sphere(ST_MakePoint(i.lng, i.lat),ST_MakePoint(:lng,:lat)) <= :radiusInKm",
//            nativeQuery = true)
//    List<Itinerary> findByLineAndSpotInRadius(@Param("lat") Double lat, @Param("lng") Double lng, @Param("radiusInKm") Long radiusInKm);



}
