package com.infygo.creditcard.service;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infygo.creditcard.DTO.CreditCardDTO;
import com.infygo.creditcard.entity.CreditCard;
import com.infygo.creditcard.exception.AlreadyExistsException;
import com.infygo.creditcard.exception.InvalidCredentialsException;
import com.infygo.creditcard.exception.NotExstingException;
import com.infygo.creditcard.repository.CreditCardRepository;



@Service
@PropertySource("classpath:validation.properties")
public class CreditCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;	
	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper modelMapper;
	
	
	//checks if card already exists or not if not then adds new one
	public void addNewCard(CreditCardDTO creditCard) throws  AlreadyExistsException{
		CreditCard Card = creditCardRepository.findByCardNumberAndCardHolderName(
				creditCard.getCardNumber(), creditCard.getCardHolderName());
		if(Card!=null) {
			throw new AlreadyExistsException(environment.getProperty("Already.Exists"));
}
		CreditCard card = modelMapper.map(creditCard, CreditCard.class);
		creditCardRepository.save(card);
	
	}
	
	//Updates the credit card total bill based on ticket booking
	@Transactional
	public void updateBill(double bill,String creditCardNumber ) throws NotExstingException {
			CreditCard creditCard = creditCardRepository.findByCardNumber(creditCardNumber);
			if(creditCard==null)
				throw new NotExstingException(environment.getProperty("No.CreditCard"));
			creditCard.setTotalBill(String.valueOf(Integer.parseInt(creditCard.getTotalBill())+(int)bill));
			creditCardRepository.save(creditCard);
		
	}
	
	//Pays bill by checking required condition
	@Transactional
	public String makePayment(CreditCardDTO creditCard ) throws InvalidCredentialsException, NotExstingException {
		
		CreditCard Card = creditCardRepository.findByCardNumberAndCardHolderName(
						creditCard.getCardNumber(), creditCard.getCardHolderName());
		if(Card==null) {
			throw new NotExstingException(environment.getProperty("No.CreditCard"));
		}
		if(!(Card.getApin().equals(creditCard.getApin()) && Card.getCvv().equals(creditCard.getCvv())))
			throw new InvalidCredentialsException("Invalid.Credentials");
		if(Integer.parseInt(Card.getTotalBill())==0) {
			return environment.getProperty("No.Dues");
		}
		Card.setTotalBill("0");
		creditCardRepository.save(Card);
		return environment.getProperty("Paid.Success");
	}
}


