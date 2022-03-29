package com.weiler.itinerarydimed.controllers;

import com.weiler.itinerarydimed.dto.ItineraryDto;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import com.weiler.itinerarydimed.repositorys.ItineraryRepository;
import com.weiler.itinerarydimed.services.BusService;
import com.weiler.itinerarydimed.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

	@Autowired
	BusService busService;

	@Autowired
	ItineraryService itineraryService;

	@Autowired
	ItineraryRepository repository;

	@GetMapping("/import/{idLinha}")
	public ResponseEntity<?> importItinerario(@PathVariable("idLinha") Long idLinha) throws IOException {
			itineraryService.importItinerary(idLinha);
			return new ResponseEntity(repository.findAll(), HttpStatus.OK);

	}

	@GetMapping("/list/{idLinha}")
	public ResponseEntity<?> importandPostItinerario(@PathVariable("idLinha") Long idLinha) throws IOException {
		itineraryService.listItinerarySecundary(idLinha);
		return new ResponseEntity(repository.findAll(), HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<Itinerary> registerOrUpdate(@RequestBody Itinerary itinerary) {
		itineraryService.saveorUpdate(itinerary);
		return ResponseEntity.ok().body(itinerary);
	}

	@DeleteMapping("{id}/delete")
	public void delete(Itinerary itinerary) {
		itineraryService.delete(itinerary);
	}

//
//	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody ResponseEntity<?> getSpotsOfBusline(@Validated @RequestBody ItineraryDto dto) throws Exception {
//			return new ResponseEntity<List<Itinerary>>(itineraryService.findSpotsByBusline(dto), HttpStatus.OK);
//	}
//
//	@PostMapping(value = "/search/intinerary", produces = MediaType.APPLICATION_JSON_VALUE)
//
//	public @ResponseBody ResponseEntity<?> getBusLinesByLocation(@Validated @RequestBody ItineraryDto dto) {
//		return new ResponseEntity<List<Bus>>(busService.findBuslinesBySpot(dto), HttpStatus.OK);
//
//	}
//
	
}
