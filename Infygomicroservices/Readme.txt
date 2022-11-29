Bodies used by requests

1)Flights:
{
	"flightId":"",
	"airlines" : "",
	"arrivalTime":"",
	"departureTime":"",
	"destination":"",
	"fare":"",
	"flightAvailableDate":"",
	"seatCount":"",
	"source" :""
}
2)User:
{
	"cardNumber" : "7654321",
    	"apin":"1111",
	"cardHolderName":"user2",
	"cvv":"132",
	"expiryMonth":"06",
	"expiryYear":"2023",
	"totalBill":"0"
}
3)Ticket:
{
	"pnr":"",
	"bookingdate" : "",
	"departureDate":"",
	"departureTime":"",
	"noOfSeats":"",
	"totalFare":""
}
4)Users
{
	"userId":"",
	"city" : "",
	"email":"",
	"name":"",
	"password":"",
	"phone":""
}
5)Ticket Booking
[
	{
        "passangerId":103,
        "passangeraAge" : "12",
        "passangerGender":"Male",
        "passangerName":"ABC"
	},
      {
        "passangerId":104,
        "passangeraAge" : "13",
        "passangerGender":"Male",
        "passangerName":"PQR"
	}
]
6)Bill payment and cardDetails
{
	"cardNumber" : "",
    "apin":"",
	"cardHolderName":"",
	"cvv":"",
	"expiryMonth":"",
	"expiryYear":"",
	"totalBill":""
}

Uri's:
http://localhost:9000/infygo/newCreditCard =>give 6) as request body
http://localhost:9000/infygo/payments  =>give card details as body  give 6) as request body
http://localhost:9000/infygo/flights/addNewFlight =>give 1) as request body
http://localhost:9000/infygo/flights/sources
http://localhost:9000/infygo/flights/destinations
http://localhost:9000/infygo/flights/{source};source=Mumbai/{destination};destination=Delhi/{journeydate};journeydate=2023-09-01
http://localhost:9000/infygo/flights/details/{flightId} 
http://localhost:9000/infygo/flights/addpassanger/{flightId}/{seats}
http://localhost:9000/infygo/book/{FlightId}/{UserId}/{creditCardNumber}=>give passangers list 5) in body (can add any number of passangers))
http://localhost:9000/infygo/registeruser =>give 2) as request body
http://localhost:9000/infygo/loginuser/{userId}/{password} 
http://localhost:9000/infygo/userdetails/{userId}


Note:Circuit breaker and loadbalancer dependancies are in ticket microservices
     micrometer and prometheus dependancies are in all the micro services