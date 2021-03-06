package com.weiler.itinerary.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.weiler.itinerary.dto.BusDto;
import com.weiler.itinerary.dto.ItineraryDto;
import com.weiler.itinerary.entities.Itinerary;
import com.weiler.itinerary.repositorys.BusRepository;
import com.weiler.itinerary.repositorys.ItineraryRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class ItineraryService {

	@Autowired
	ItineraryRepository repository;

	@Autowired
	BusRepository busRepository;

	public ItineraryDto importItineraryPrimary(Long idLinha) throws IOException {
		String theUrl = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
		URL url = new URL(theUrl + idLinha);
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		InputStream in = uc.getInputStream();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(result);
		List<BusDto> bus = new ArrayList<>();
		for (Integer i = 0; i < jsonNode.size(); i++) {
			String idBus = i.toString();
			if (jsonNode.get(idBus) != null) {
				String latitude = jsonNode.get(idBus).get("lat").asText();
				String longitude = jsonNode.get(idBus).get("lng").asText();
				BusDto busDTO = new BusDto(idBus, latitude, longitude);
				bus.add(busDTO);
			}
		}

		String nome = jsonNode.get("nome").asText();
		String codigo = jsonNode.get("codigo").asText();
		ItineraryDto itineraryDto = new ItineraryDto(idLinha.toString(), codigo, nome, bus);
		return itineraryDto;

	}

	public void importItinerary(Long idLinha) throws IOException {
		try {
			ItineraryDto itineraryDto = importItineraryPrimary(idLinha);

			for (BusDto busDTO : itineraryDto.getPontos()) {
				Itinerary itinerary = repository.findByIndiceAndLinha(
						Long.parseLong(itineraryDto.getIdlinha()),
						Long.parseLong(busDTO.getId()));
				if (itinerary == null) {
					itinerary = new Itinerary();
				}
				itinerary.setIndice(Long.parseLong(busDTO.getId()));
				itinerary.setLat(Double.parseDouble(busDTO.getLatitude()));
				itinerary.setLng(Double.parseDouble(busDTO.getLongitude()));
				itinerary.setBus(busRepository.findById(Long.parseLong(itineraryDto.getIdlinha())).get());
				repository.save(itinerary);
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void saveorUpdate(Itinerary itinerary) {

		try {
			Itinerary itinerary1 = repository.findItineraryById(itinerary.getId());
			if (!itinerary1.equals(itinerary)) {
				itinerary1.setLat(itinerary.getLat());
				itinerary1.setLng(itinerary.getLng());
				itinerary1.setIndice(itinerary.getIndice());
				repository.save(itinerary1);
			}
		} catch (NoSuchElementException e) {
			repository.save(itinerary);
		}
	}

	public void delete(Itinerary itinerary) {
		repository.delete(itinerary);
	}


//
//	public void importList(Long idLinha) throws IOException {
//		try {
//			ItineraryDto itineraryDto = importItineraryPrimary(idLinha);
//
//			for (BusDto busDTO : itineraryDto.getPontos()) {
//				Itinerary itinerary = repository.findByIndiceAndLinha(
//						Long.parseLong(itineraryDto.getIdlinha()),
//						Long.parseLong(busDTO.getId()));
//				if (itinerary == null) {
//					itinerary = new Itinerary();
//				}
//				itinerary.setIndice(Long.parseLong(busDTO.getId()));
//				itinerary.setLat(Double.parseDouble(busDTO.getLatitude()));
//				itinerary.setLng(Double.parseDouble(busDTO.getLongitude()));
//				itinerary.setBus(busRepository.findById(Long.parseLong(itineraryDto.getIdlinha())).get());
//				repository.findAll(itinerary);
//			}
//
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}

}


