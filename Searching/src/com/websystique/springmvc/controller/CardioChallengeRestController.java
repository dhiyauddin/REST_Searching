package com.websystique.springmvc.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.CardioChallenge;
import com.websystique.springmvc.service.CardioChallengeService;

@RestController
public class CardioChallengeRestController {

	@Autowired
	CardioChallengeService cardioService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All
	// cardios--------------------------------------------------------

	@RequestMapping(value = "/cardio/", method = RequestMethod.GET)
	public ResponseEntity<List<CardioChallenge>> listAllCardios() {
		List<CardioChallenge> cardios = cardioService.findAllCardios();
		if (cardios.isEmpty()) {
			return new ResponseEntity<List<CardioChallenge>>(HttpStatus.NO_CONTENT);// You many decide to return
																					// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<CardioChallenge>>(cardios, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// cardio--------------------------------------------------------

	@RequestMapping(value = "/cardio/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CardioChallenge> getCardio(@PathVariable("id") long id) {
		System.out.println("Fetching cardio with id " + id);
		CardioChallenge cardio = cardioService.findById(id);
		if (cardio == null) {
			System.out.println("cardio with id " + id + " not found");
			return new ResponseEntity<CardioChallenge>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CardioChallenge>(cardio, HttpStatus.OK);
	}

	// -------------------Create a
	// cardio--------------------------------------------------------

	@RequestMapping(value = "/cardio/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCardio(@RequestBody CardioChallenge cardio, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating cardio " + cardio.getName());

		if (cardioService.isCardioExist(cardio)) {
			System.out.println("A cardio with name " + cardio.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		cardioService.saveCardio(cardio);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/cardio/{id}").buildAndExpand(cardio.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a cardio
	// --------------------------------------------------------

	@RequestMapping(value = "/cardio/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CardioChallenge> updateCardio(@PathVariable("id") long id, @RequestBody CardioChallenge cardio) {
		System.out.println("Updating cardio " + id);

		CardioChallenge currentcardio = cardioService.findById(id);

		if (currentcardio == null) {
			System.out.println("cardio with id " + id + " not found");
			return new ResponseEntity<CardioChallenge>(HttpStatus.NOT_FOUND);
		}

		currentcardio.setName(cardio.getName());
		currentcardio.setLevel(cardio.getLevel());

		cardioService.updateCardio(currentcardio);
		return new ResponseEntity<CardioChallenge>(currentcardio, HttpStatus.OK);
	}

	// ------------------- Delete a cardio
	// --------------------------------------------------------

	@RequestMapping(value = "/cardio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CardioChallenge> deleteCardio(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting cardio with id " + id);

		CardioChallenge cardio = cardioService.findById(id);
		if (cardio == null) {
			System.out.println("Unable to delete. cardio with id " + id + " not found");
			return new ResponseEntity<CardioChallenge>(HttpStatus.NOT_FOUND);
		}

		cardioService.deleteCardioById(id);
		return new ResponseEntity<CardioChallenge>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All cardio
	// --------------------------------------------------------

	@RequestMapping(value = "/cardio/", method = RequestMethod.DELETE)
	public ResponseEntity<CardioChallenge> deleteAllCardios() {
		System.out.println("Deleting All cardios");

		cardioService.deleteAllCardios();
		return new ResponseEntity<CardioChallenge>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/cardio/{level}", method = RequestMethod.GET)
	public ResponseEntity<List<CardioChallenge>> listAllCardiosByName(@PathVariable("level") String level) {
		System.out.println("Fetching cardio with level " + level);
		List<CardioChallenge> cardios = cardioService.findAllCardiosByName(level);
		if (cardios.isEmpty()) {
			return new ResponseEntity<List<CardioChallenge>>(HttpStatus.NO_CONTENT);// You many decide to return																					// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<CardioChallenge>>(cardios, HttpStatus.OK);
	}
	
}
