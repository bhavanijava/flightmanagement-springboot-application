package com.cg.flightmgmt.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flightmgmt.dao.FlightDao;
import com.cg.flightmgmt.dto.Flight;
import com.cg.flightmgmt.exception.FlightNotFoundException;

@RestController
@RequestMapping("/flight")
public class FlightController {
Logger logger=org.slf4j.LoggerFactory.getLogger(FlightController.class);
	
	@Autowired
	private FlightDao dao;
	
	//localhost:5010/flight/createFlight
	@PostMapping(path="/createFlight")
	public Flight createFlight(@Valid @RequestBody Flight flight) {
		logger.info("Flight details added to the database");
		return dao.addFlight(flight);
	}
	
	//localhost:5010/flight/flights
	@RequestMapping(path="/flights")
	public List<Flight> viewAllFlights(){
		return dao.viewFlight();
	}
	
	//localhost:5010/flight/flights/:id
	@GetMapping(path="/flights/{flightno}")
	public Flight viewFlightById(@PathVariable BigInteger flightno) throws FlightNotFoundException{
		return dao.viewFlight(flightno);
	}
	
	//localhost:5010/flight/updateFlight/:id
	@PutMapping(path="/updateFlight/{id}")
	public Flight updateFlight(@RequestBody Flight flight, @PathVariable int id) {
		logger.info("Flight details modified sucessfully");
		return dao.modifyFlight(flight);
	}
	
	//localhost:5010/flight/deleteFlight/:id
	@DeleteMapping(path="deleteFlight/{flightno}")
	public void deleteFlight(@PathVariable BigInteger flightno){
		logger.info("Flight details removed from the database");
		dao.deleteFlight(flightno);
	}
}