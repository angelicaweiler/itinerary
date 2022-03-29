package com.weiler.itinerarydimed.controllers;

import com.weiler.itinerarydimed.dto.ItineraryDto;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.entities.Itinerary;
import com.weiler.itinerarydimed.services.BusService;
import com.weiler.itinerarydimed.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	BusService busService;

	@Autowired
	ItineraryService itineraryService;

	@GetMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getBuss() throws Exception {
		return new ResponseEntity<List<Bus>>(busService.importBus(), HttpStatus.OK);
	}

	@GetMapping("/{nome}")
	public @ResponseBody ResponseEntity<?> getBusByName(@PathVariable("nome") String nome) throws Exception {
		return new ResponseEntity<List<Bus>>(busService.listByName(nome), HttpStatus.OK);
	}

	@PostMapping("/register")
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

	

//
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

	
}
