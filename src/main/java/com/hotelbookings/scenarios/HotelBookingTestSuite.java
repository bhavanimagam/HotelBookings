package com.hotelbookings.scenarios;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "classpath:features",
        glue="com.hotelbookings.scenarios",
        format = {"pretty", "json:reports/hotel_booking/hote_booking.json"},
        tags = {"@eentest"}

)
public class HotelBookingTestSuite {

}
