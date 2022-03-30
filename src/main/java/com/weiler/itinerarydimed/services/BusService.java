package com.weiler.itinerarydimed.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.repositorys.BusRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BusService {

	@Autowired
	BusRepository busRepository;


	public List<Bus> importBus() throws Exception {
		String theUrl = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%25&t=o";
		URL url = new URL(theUrl);
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		InputStream in = uc.getInputStream();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = IOUtils.toString(in, StandardCharsets.UTF_8);
		Bus[] incomeLines = gson.fromJson(result, Bus[].class);
		List<Bus> incomeLinesList = Arrays.asList(incomeLines);

		if (!incomeLinesList.isEmpty()) {
			List<Bus> bus = busRepository.saveAll(incomeLinesList);

		}
		return incomeLinesList;
	}

	public void  saveorUpdate(Bus bus) {

		try {
			Bus busRepo = busRepository.findBusById(bus.getId());
			if (!busRepo.equals(bus)) {
				busRepo.setCodigo(bus.getCodigo());
				busRepo.setNome(bus.getNome());
				busRepository.save(busRepo);
			}
		} catch (NoSuchElementException e) {
			busRepository.save(bus);
		}
	}

	public List<Bus> listByName(String nome) throws Exception {
		List<Bus> busList = busRepository.findByNome(nome);
		if(busList == null || busList.isEmpty())
			throw new ChangeSetPersister.NotFoundException();;
		return busList;
	}

	public void delete(Bus bus){ busRepository.delete(bus);}

//
//
//
//	public List<Bus> findBuslinesBySpot(ItineraryDto dto) {
//		return busRepository.findBySpotInRadius(dto.getLat(), dto.getLng(), dto.getRadiusInMeters());
//	}


}
