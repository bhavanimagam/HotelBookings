package com.hotelbookings.scenarios;

import com.hotelbookings.pages.ChromeDriverFactory;
import com.hotelbookings.pages.HotelBookingPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class HotelBookingStepDef {

    private WebDriver driver;
    private HotelBookingPage hotelBookingPage;

    @Before
    public void setup() {
        driver = ChromeDriverFactory.getWebDriver();
        hotelBookingPage = new HotelBookingPage(driver);
    }

    @Given("^I access equal experts hotel booking site$")
    public void I_access_experis_hotel_booking_site() throws Throwable {
        hotelBookingPage.launch();
    }


    @When("^I enter the fields with the values '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)' and click on Save$")
    public void i_enter_the_fields_with_the_values_and_click_on_Save(String firstName, String lastName, String price,
                                                           String deposit, String checkIn, String checkOut) throws Throwable {
        hotelBookingPage.createBooking(firstName, lastName, price, deposit, checkIn, checkOut);

    }

    @Then("^I should find a booking with the values '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)'$")
    public void i_should_find_a_booking_with_the_values(String firstName, String lastName, String price,
                                                                   String deposit, String checkIn, String checkOut) throws Throwable {
        hotelBookingPage.verifyBookingExists(firstName, lastName, price, deposit, checkIn, checkOut);

    }

    @When("^I delete the booking with the value '(.*)'$")
    public void i_delete_the_booking_with_the_value(String firstName) throws Throwable {
        hotelBookingPage.deleteBooking(firstName);
    }

    @Then("^I should not find a booking with the value '(.*)'$")
    public void i_should_not_find_a_booking_with_the_value(String firstName) throws Throwable {
        hotelBookingPage.verifyBookingDoesNotExist(firstName);

    }

    @After
    public void tearDown() {
        driver.quit();
        hotelBookingPage = null;
    }
}
