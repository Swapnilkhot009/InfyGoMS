package com.infygo.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infygo.creditcard.entity.CreditCard;
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

	@Query
	public CreditCard findByCardNumber(String number);
	@Query
	public CreditCard findByCardNumberAndCardHolderName(String number, String name);
	

}
