package com.infygo.flights.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infygo.flights.DTO.FlightDTO;
import com.infygo.flights.entity.Flight;
import com.infygo.flights.exception.AlreadyExistsException;
import com.infygo.flights.exception.FlightUnavailabeException;
import com.infygo.flights.repository.FlightRepository;


@Service
@PropertySource("classpath:validation.properties")
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper modelMapper;
	
	//Provides the available flight details based on given source and destination
	public FlightDTO addNewFlight(FlightDTO flightDTO) throws AlreadyExistsException{
		Flight flight = flightRepository.findById(flightDTO.getFlightId()).orElse(null);
		if(flight!=null)
			throw new AlreadyExistsException(environment.getProperty("Already.Exists"));
		flight = modelMapper.map(flightDTO, Flight.class);
		flightRepository.save(flight);
		return modelMapper.map(flight,FlightDTO.class);
	}
	
	//Provides the available flight sources
	public Set<String> getFlightSources() {
		Set<String> sourcesList = flightRepository.findAll().stream().
				map(flight->flight.getSource()).collect(Collectors.toSet());
		return sourcesList; 
	}
	
	//Provides the available flight destinations
	public Set<String> getFlightDestinations() {
		Set<String> sourcesList = flightRepository.findAll().stream().
				map(flight->flight.getDestination()).collect(Collectors.toSet());
		return sourcesList; 
	}
	
	//Provides the available flight details based on given source and destination
	public Set<FlightDTO>  getflightFromSrcToDestOnDate(List<String> sourceSet, List<String> destinationSet,
			List<LocalDate> journeyDateSet) throws FlightUnavailabeException {
		
		Set<FlightDTO> flightDTOs=new HashSet<>();
		
		for(int i=0,j=0;i<sourceSet.size()&&j<destinationSet.size();i++,j++) {
			Set<Flight> flights =flightRepository.findBySourceAndDestinationAndFlightAvailableDate
			(sourceSet.get(i),destinationSet.get(i),journeyDateSet.get(i));
			for(Flight flight:flights) {
				flightDTOs.add(modelMapper.map(flight, FlightDTO.class));
			}
		}	
		if(flightDTOs.isEmpty())
			throw new FlightUnavailabeException(environment.getProperty("Flight.Unavailable"));
		return flightDTOs;
	}
	
	
	//Provides details of flight based on flightId also required in ticket booking
	public FlightDTO getflightDeailsById(String flightId) throws FlightUnavailabeException {
		Flight flight =flightRepository.findById(flightId).orElse(null);
		if(flight==null)
			throw new FlightUnavailabeException(environment.getProperty("Flight.Unavailable"));
		return modelMapper.map(flight,FlightDTO.class);
	}

	//Required in ticket booking to update number of seats
	@Transactional
	public void updateSeats(String flightId,Integer seats) {
		Flight flight = flightRepository.findById(flightId).orElse(null);
		flight.setSeatCount(flight.getSeatCount()-seats);
		flightRepository.save(flight);
	}
}