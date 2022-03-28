package com.weiler.itinerarydimed.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDto implements Serializable {

	private static final long serialVersionUID = 8903508018512975372L;

	private Double lat;
	private Double lng;
	private Long radiusInMeters;
	private Long lineId;

	Map<String, Object> details = new LinkedHashMap<>();

	@JsonAnySetter
	void setDetail(String key, Object value) {
		details.put(key, value);
	}

	public Map<String, Object> getDetails() {
		return details;
	}
	
}
