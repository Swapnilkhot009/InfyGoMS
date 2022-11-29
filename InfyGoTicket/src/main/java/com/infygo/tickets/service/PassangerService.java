package com.infygo.tickets.service;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.infygo.tickets.DTO.PassangerDTO;
import com.infygo.tickets.entity.Passanger;
import com.infygo.tickets.repository.PassangerRepository;

@Service
@PropertySource("classpath:validation.properties")
public class PassangerService { 
	@Autowired
	private PassangerRepository passangerRepository; 
	@Autowired
	private ModelMapper modelMapper;
	//Adds the passengers in passenger database
	public void addPassanger(Set<PassangerDTO> passangerDTOs,Integer pnr) {
		for(PassangerDTO passangerDTO:passangerDTOs) {
			Passanger passanger = modelMapper.map(passangerDTO, Passanger.class);
			passanger.setTicketPnr(pnr);
			passangerRepository.save(passanger);
		}
		
	}
}