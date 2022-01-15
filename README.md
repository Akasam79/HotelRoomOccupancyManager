# HotelRoomOccupancyManager

This is a Service that provides an interface for hotels to enter the numbers of
Premium and Economy rooms that are available and immediately returns how many rooms of each category will be occupied and the sum of money they will make in total for each one of the categories.

## Run Tests...

mvn clean

mvn install

mvn test

## To Test API using an API testing tool (Postman)...

RequestBody should be in Json format as shown below.

{ <br />&nbsp;&nbsp;&nbsp;&nbsp;"availableEconomyRoom": 5, <br />&nbsp;&nbsp;&nbsp;&nbsp;"availablePremiumRoom": 7 <br />
}

The above request represents the number of Premium and Economy rooms available and the request URL should be http://localhost:8080/