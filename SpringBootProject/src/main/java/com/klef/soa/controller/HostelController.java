package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Hostel;
import com.klef.soa.service.HostelService;

@RestController
@RequestMapping("/hostel")
public class HostelController {
	@Autowired
	private HostelService service;
	
	@GetMapping("/")
	public String demo()
	{
		return "Spring Boot Project";
	}
	
	@PostMapping("/add")
	public ResponseEntity<Hostel> addHostel(@RequestBody Hostel h)
	{
		Hostel hostel = service.addHostel(h);
		return ResponseEntity.status(201).body(hostel);
	}

	@GetMapping("/displayall")
	public ResponseEntity<List<Hostel>> displayAllHostels()
	{
		List<Hostel> hostels = service.displayAllHostels();
		return ResponseEntity.status(200).body(hostels);
	}

	@GetMapping("/display/{id}")
	public ResponseEntity<?> displayById(@PathVariable Long id)
	{
		Hostel hostel = service.displayById(id);

		if (hostel != null)
		{
			return ResponseEntity.status(200).body(hostel);
		}
		else
		{
			return ResponseEntity.status(404).body("Hostel ID Not Found");
		}
	}

	
	@PutMapping("/update")
	public ResponseEntity<?> updateHostel(@RequestBody Hostel hostel)
	{
		Hostel h = service.updateHostel(hostel);

		if (h != null)
		{
			return ResponseEntity.ok(h);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hostel ID not found");
		}
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteHostelById(@PathVariable Long id)
	{
		String message = service.deleteHostelById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/displaybytype/{type}")
	public ResponseEntity<List<Hostel>> displayByType(@PathVariable String type)
	{
		List<Hostel> hostels = service.findByType(type);
		return ResponseEntity.status(200).body(hostels);
	}
	
	@GetMapping("/displaybyname/{name}")
	public ResponseEntity<List<Hostel>> displayByName(@PathVariable String name)
	{
		List<Hostel> hostels = service.findByName(name);
		return ResponseEntity.status(200).body(hostels);
	}
}
