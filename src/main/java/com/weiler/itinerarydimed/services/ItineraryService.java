package com.weiler.itinerarydimed.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weiler.itinerarydimed.dto.ItineraryDto;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import com.weiler.itinerarydimed.repositorys.BusRepository;
import com.weiler.itinerarydimed.repositorys.ItineraryRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

	public boolean runImport() {
		try {
			for (Bus bus : busRepository.findAll()) {
				List<Itinerary> importedSpots = importPoaSpotsByLine(bus);
				if (importedSpots == null || importedSpots.isEmpty())
					continue;

				for (Itinerary item : importedSpots)
					updateSpotCreateIfNotExists(item, bus);
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private void updateSpotCreateIfNotExists(Itinerary item, Bus busLine) throws Exception {
		try {
			Itinerary dbModel = repository.findById(item.getId()).get();

			updateSpotIfNeeded(item, dbModel);
			updataRelationship(busLine, dbModel);

		} catch (NoSuchElementException e) {
			Itinerary dbModel = repository.save(item);
			updataRelationship(busLine, dbModel);
		}
	}

	private void updataRelationship(Bus busLine, Itinerary spotDbModel) {// todo
		if (busLine.getItineraries() == null)
			busLine.setItineraries(new HashSet<>());
		if (!busLine.getSpots().contains(spotDbModel))
			busLine.getSpots().add(spotDbModel);
		busRepository.save(busLine);
	}

	private void updateSpotIfNeeded(Itinerary dtoItem, Itinerary dbModel) {// todo

		if (!dbModel.fullEquals(dtoItem)) {
			dbModel.setLat(dtoItem.getLat());
			dbModel.setLng(dtoItem.getLng());
			repository.save(dbModel);
		}

	}

	private List<Itinerary> importPoaSpotsByLine(Bus busLine) throws Exception {
		String theUrl = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
		URL url = new URL(theUrl + busLine.getId());
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		InputStream in = uc.getInputStream();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);

		List<Itinerary> incomeSpotsList = spotResultToList(result, busLine);
		return incomeSpotsList;
	}

	private List<Itinerary> spotResultToList(String result, Bus busLine)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		ItineraryDto map = mapper.readValue(result, ItineraryDto.class);

		List<Itinerary> incomeSpotsList = new ArrayList<>();
		int i = 0;
		while (map.getDetails().containsKey("" + i)) {
			incomeSpotsList.add(convertSpotMapToModel(busLine, map, i++));

		}
		return incomeSpotsList;
	}

	private Itinerary convertSpotMapToModel(Bus busLine, ItineraryDto map, int i) {
		Map innerMap = (Map) (map.getDetails().get("" + i));
		Double lat = Double.valueOf((String) innerMap.get("lat"));
		Double lng = Double.valueOf((String) innerMap.get("lng"));
		return new Itinerary(Long.valueOf(i), busLine, lat, lng);
	}

	public List<Itinerary> findSpotsByBusline(ItineraryDto lineDto) throws Exception {
		if(lineDto.getLineId() == null)
			throw new ChangeSetPersister.NotFoundException();
		return repository.findByLineAndSpotInRadius(lineDto.getLat(), lineDto.getLng(), lineDto.getLineId(), lineDto.getRadiusInMeters());
	}

}
