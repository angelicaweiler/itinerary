package com.weiler.itinerarydimed.dto;

import com.weiler.itinerarydimed.entities.Itinerary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {



    private String id;

    private String latitude;

    private String longitude;



}
