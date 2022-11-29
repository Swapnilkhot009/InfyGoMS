package com.infygo.creditcard.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.creditcard.DTO.CreditCardDTO;
import com.infygo.creditcard.exception.AlreadyExistsException;
import com.infygo.creditcard.exception.InvalidCredentialsException;
import com.infygo.creditcard.exception.NotExstingException;
import com.infygo.creditcard.service.CreditCardService;

@RestController
@RequestMapping("/infygo")
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditCardService;
	@Autowired
	private Environment environment;
	
	//Adds new credit card by taking card details as requestbody
	@PostMapping("/newCreditCard")
	public ResponseEntity<String> addCard(@Valid @RequestBody CreditCardDTO creditCard) throws InvalidCredentialsException, AlreadyExistsException {
		
		creditCardService.addNewCard(creditCard);
		
		return ResponseEntity.ok(environment.getProperty("New.Card.Added"));
	}
	
	//Takes card details via card request body validates them and then pays the due amount
	@PostMapping("/payment")
	public ResponseEntity<String> payment(@RequestBody CreditCardDTO creditCard) throws InvalidCredentialsException, NotExstingException {
		return ResponseEntity.ok(creditCardService.makePayment(creditCard));
	}
	
	//Usage during ticket booking, adds the total bill in given creditcard
	@PutMapping("/addbill/{bill}/{creditCardNumber}")
	public ResponseEntity<String> addMyBill(@PathVariable("bill") double bill,
			@PathVariable("creditCardNumber") String creditCardNumber) throws InvalidCredentialsException, NotExstingException{
		creditCardService.updateBill(bill,creditCardNumber);
		return ResponseEntity.ok(environment.getProperty("Bill.add"));
	}
	
}
