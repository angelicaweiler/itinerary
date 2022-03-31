package com.weiler.itinerarydimed.controllers;

import com.weiler.itinerarydimed.controllers.documentation.BusControllerDocumentation;
import com.weiler.itinerarydimed.entities.Bus;
import com.weiler.itinerarydimed.services.BusService;
import com.weiler.itinerarydimed.services.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bus")
@AllArgsConstructor
public class BusController implements BusControllerDocumentation {

	@Autowired
	BusService busService;

	@Autowired
	ItineraryService itineraryService;

	@GetMapping("/import")
	public @ResponseBody ResponseEntity<?> importBus() throws Exception {
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
	public ResponseEntity delete(Bus bus) {
		busService.delete(bus);
		return ResponseEntity.ok(bus);
	}

	
}
