package com.weiler.itinerarydimed.repositorys;


import com.weiler.itinerarydimed.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    List<Bus> findByNomeContainingIgnoreCase(String nome);

    Bus findBusById (Integer id);

    List<Bus> findByNome(String nome);

    @Query(value=
            "SELECT distinct b.* " +
                    "FROM bus_line b " +
                    "inner join spot s on b.id = s.line_id " +
                    "WHERE st_distance_sphere(ST_MakePoint(s.lng, s.lat),ST_MakePoint(:lng, :lat)) <= :radiusInMeters ",
            nativeQuery = true)
    List<Bus> findBySpotInRadius(@Param("lat") Double lat, @Param("lng") Double lng, @Param("radiusInMeters") Long radiusInMeters);

}

