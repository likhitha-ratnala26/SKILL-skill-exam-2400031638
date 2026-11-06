package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.Hostel;
import com.klef.soa.service.HostelService;

@RestController
@RequestMapping("/hostel")
public class HostelController {

    @Autowired
    private HostelService service;

    @GetMapping("/")
    public String demo() {
        return "Spring Boot Project";
    }

    // INSERT
    @PostMapping("/add")
    public ResponseEntity<Hostel> addHostel(@RequestBody Hostel h) {

        Hostel hostel = service.addHostel(h);

        return ResponseEntity.status(HttpStatus.CREATED).body(hostel);
    }

    // DISPLAY ALL
    @GetMapping("/displayall")
    public ResponseEntity<List<Hostel>> displayAllHostels() {

        return ResponseEntity.ok(service.displayAllHostels());
    }

    // DISPLAY BY ID
    @GetMapping("/display/{id}")
    public ResponseEntity<?> displayById(@PathVariable Long id) {

        Hostel hostel = service.displayById(id);

        if (hostel != null) {
            return ResponseEntity.ok(hostel);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Hostel ID Not Found");
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> updateHostel(@RequestBody Hostel hostel) {

        Hostel updated = service.updateHostel(hostel);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Hostel ID not found");
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHostelById(@PathVariable Long id) {

        String message = service.deleteHostelById(id);

        if (message.equals("Hostel deleted successfully")) {
            return ResponseEntity.ok(message);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    // FIND BY TYPE
    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<Hostel>> displayByType(
            @PathVariable String type) {

        return ResponseEntity.ok(service.findByType(type));
    }

    // FIND BY NAME
    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<Hostel>> displayByName(
            @PathVariable String name) {

        return ResponseEntity.ok(service.findByName(name));
    }
}