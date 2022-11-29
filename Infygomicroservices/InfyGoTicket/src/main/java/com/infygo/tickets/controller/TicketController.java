package com.infygo.tickets.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.tickets.DTO.PassangerDTO;
import com.infygo.tickets.DTO.TicketDTO;
import com.infygo.tickets.exception.FlightUnavailabeException;
import com.infygo.tickets.exception.InvalidCredentialsException;
import com.infygo.tickets.exception.NotExstingException;
import com.infygo.tickets.service.TicketService;
@RestController
@RequestMapping("/infygo")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	//Books ticket 
	@PostMapping("/book/{FlightId}/{UserId}/{creditCardNumber}")
	public ResponseEntity<TicketDTO> book(
			@PathVariable("FlightId")String flightId,
			@PathVariable("UserId")String userId,
			@PathVariable("creditCardNumber")String creditCardNumber,
			@RequestBody Set<PassangerDTO> passangers) 
			throws InvalidCredentialsException, FlightUnavailabeException, NotExstingException{
		return ResponseEntity.ok(ticketService.bookticket(flightId,userId,passangers,creditCardNumber));
	}
}
