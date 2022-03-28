package com.weiler.itinerarydimed.controllers;

import com.weiler.itinerarydimed.dto.ItineraryDto;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import com.weiler.itinerarydimed.repositorys.BusRepository;
import com.weiler.itinerarydimed.services.BusService;
import com.weiler.itinerarydimed.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bus")
public class BusLineController {

	@Autowired
	BusService busService;

	@Autowired
	ItineraryService itineraryService;

	BusRepository repo;



	@GetMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getBuss() throws Exception {
		return new ResponseEntity<List<Bus>>(busService.importBus(), HttpStatus.OK);
	}

	@GetMapping("/search/nome")
	public ResponseEntity<List<Bus>> getBusByName(@RequestParam String nome) {
		return new ResponseEntity<List<Bus>>(repo.findByNome(nome), HttpStatus.OK);
	}

	@PutMapping("/register")
	public ResponseEntity<Bus> registerOrUpdate(@RequestBody Bus bus) {
		busService.saveorUpdate(bus);
		return ResponseEntity.ok().body(bus);
	}

	@DeleteMapping("{id}/delete")
	public void delete(Bus bus) {
		busService.delete(bus);
	}


	


	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getBusLines() throws Exception {
		return new ResponseEntity<List<Bus>>(busService.listAll(), HttpStatus.OK);
		
	}

//
//	@PostMapping(value = "/updates")
//	public void updateBus(@RequestBody Bus bus) throws Exception {
//		busService.updateLineCreateIfNotExists(bus);
//	}
//

	

	@GetMapping(value = "/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getBusLinesWithFilter(@PathVariable("filter") String filter) throws Exception {
		return new ResponseEntity<List<Bus>>(busService.listAll(filter), HttpStatus.OK);
			}
	

	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getSpotsOfBusline(@Validated @RequestBody ItineraryDto dto) throws Exception {
			return new ResponseEntity<List<Itinerary>>(itineraryService.findSpotsByBusline(dto), HttpStatus.OK);
	}

	@PostMapping(value = "/search/intinerary", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody ResponseEntity<?> getBusLinesByLocation(@Validated @RequestBody ItineraryDto dto) {
		return new ResponseEntity<List<Bus>>(busService.findBuslinesBySpot(dto), HttpStatus.OK);

	}

	
}
