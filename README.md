# HotelBookings

#Chrome driver factory class

It is the driver util based on operating system it selects the executable and intiates the driver

#HotelBookingPage

It is the page object where all the selectors and bookings page related element iteractions are defined

#HotelBookingStepDef
It contains step definitions related to save and delete booking.

#Running the tests using junitRunner
To run the tests right click and run HotelBookingTestSuite.
It will run all the tests with TAG:   @eentest

# Running the tests from executable Jar

Clone the repo
Cd to executableJars
Unzip execitableJars.Zip
Cd to the unzipped location
Run the following command

java -jar hotel-bookings.jar --plugin pretty --plugin html:cucumber/html --glue com.hotelbookings.scenarios classpath:features --tags @eentest