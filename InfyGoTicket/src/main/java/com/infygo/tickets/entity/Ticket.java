package com.infygo.tickets.entity;



import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.persistence.Id;


@Entity
public class Ticket {
	@Id
	@NotNull
	private Integer pnr;
	@Column
	private LocalDate bookingdate;
	@Column
	@Future(message="Daparture is always in future")
	private LocalDate departureDate;
	@Column
	private String departureTime;
	@Column
	@Min(value=1,message="Cannot book less than Zero seats")
	@Max(value=5,message="Cannot book more than five seats")
	private Integer noOfSeats;
	@Column
	private Double totalFare;
	@Column
	private String flightId ;
	@Column
	private String userId ;
	

	public Ticket(Integer pnr, LocalDate booking_date, LocalDate departureDate, String departureTime,
			Integer noOfSeats, Double totalFare) {
		super();
		this.pnr = pnr;
		this.bookingdate = booking_date;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.noOfSeats = noOfSeats;
		this.totalFare = totalFare;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPnr() {
		return pnr;
	}

	public void setPnr(Integer pnr) {
		this.pnr = pnr;
	}

	public LocalDate getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(LocalDate bookingdate) {
		this.bookingdate = bookingdate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
