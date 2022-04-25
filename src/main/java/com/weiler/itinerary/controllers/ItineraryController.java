package com.weiler.itinerary.controllers;

import com.weiler.itinerary.controllers.documentation.ItineraryControllerDocumentation;
import com.weiler.itinerary.entities.Itinerary;
import com.weiler.itinerary.repositorys.ItineraryRepository;
import com.weiler.itinerary.services.BusService;
import com.weiler.itinerary.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/itinerary")
public class ItineraryController implements ItineraryControllerDocumentation {

	@Autowired
	BusService busService;

	@Autowired
	ItineraryService itineraryService;

	@Autowired
	ItineraryRepository repository;

	@GetMapping("/import/{idLinha}")
	public ResponseEntity<?> importItinerary(@PathVariable("idLinha") Long idLinha) throws IOException {
			itineraryService.importItinerary(idLinha);
			return new ResponseEntity(repository.findAll(), HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<Itinerary> registerOrUpdate(@RequestBody Itinerary itinerary) {
		itineraryService.saveorUpdate(itinerary);
		return ResponseEntity.ok().body(itinerary);
	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity delete(Itinerary itinerary) {
		itineraryService.delete(itinerary);
		return ResponseEntity.ok(itinerary);
	}

//	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody ResponseEntity<?> getSpotsOfBusline(@Validated @RequestBody Double latitude, Double longitude, Long raio) throws Exception {
//					return new ResponseEntity<List<Itinerary>>(itineraryService.findSpotsByBusline(latitude, longitude, raio), HttpStatus.OK);
//		}
//
}
