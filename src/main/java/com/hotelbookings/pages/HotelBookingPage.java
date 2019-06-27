package com.hotelbookings.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class HotelBookingPage {

    private WebDriver webDriver;
    private WebDriverWait wait;
    private static final By firstNameLocator = By.id("firstname");
    private static final By lastNameLocator = By.id("lastname");
    private static final By totPriceLocator = By.id("totalprice");
    private static final By depositPaidLocator = By.id("depositpaid");
    private static final By checkInLocator = By.id("checkin");
    private static final By checkOutLocator = By.id("checkout");
    private static final By saveButtonLocator = By.cssSelector("input[value=' Save ']");
    private static final By deleteButtonLocator = By.cssSelector("input[value='Delete']");

    public HotelBookingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    public void launch() {
        webDriver.get("http://hotel-test.equalexperts.io");
    }

    public void createBooking(String firstName, String lastName, String price,
                              String deposit, String checkIn, String checkOut) {

        setFirstName(firstName);
        setLastName(lastName);
        setPrice(price);
        setDeposit(deposit);
        setCheckInDate(checkIn);
        setCheckoutDate(checkOut);
        save();
    }

    public void deleteBooking(String firstName) {
        WebElement bookingId = getBookingId(firstName);
        WebElement bookingRecord = getBookingRecord(bookingId);
        bookingRecord.findElement(deleteButtonLocator).click();
        wait.until(ExpectedConditions.invisibilityOf(bookingId));
    }

    public void verifyBookingExists(String firstName, String lastName, String price,
                                    String deposit, String checkIn, String checkOut) {
        WebElement bookingId = getBookingId(firstName);
        WebElement bookingRecord = getBookingRecord(bookingId);

        WebElement firstNameField = getBookingColumn(bookingRecord, "1");
        WebElement lastNameField = getBookingColumn(bookingRecord, "2");
        WebElement priceField = getBookingColumn(bookingRecord, "3");
        WebElement depositField = getBookingColumn(bookingRecord, "4");
        WebElement checkInField = getBookingColumn(bookingRecord, "5");
        WebElement checkOutField = getBookingColumn(bookingRecord, "6");
        assertEquals("FirstName is not as expected ", firstNameField.getText(), firstName);
        assertEquals("LastName is not as expected ", lastNameField.getText(), lastName);
        assertEquals("Price is not as expected ", priceField.getText(), price);
        assertEquals("Deposit is not as expected ", depositField.getText(), deposit);
        assertEquals("CheckIn Date is not as expected ", checkInField.getText(), checkIn);
        assertEquals("CheckOut Date is not as expected ", checkOutField.getText(), checkOut);
    }

    public void verifyBookingDoesNotExist(String firstName) {
        List<WebElement> firstNameElements = xpathContainsTextElements(firstName);
        assertTrue("Booking exists when it should not, check manually ", firstNameElements.size() == 0);
    }

    private void setFirstName(String firstName) {
        webDriver.findElement(firstNameLocator).sendKeys(firstName);
    }

    private void setLastName(String lastName) {
        webDriver.findElement(lastNameLocator).sendKeys(lastName);

    }

    private void setPrice(String price) {
        webDriver.findElement(totPriceLocator).sendKeys(price);
    }

    private void setDeposit(String deposit) {
        Select select = new Select(webDriver.findElement(depositPaidLocator));
        select.selectByVisibleText(deposit);
    }

    private void setCheckInDate(String checkInDate) {
        webDriver.findElement(checkInLocator).sendKeys(checkInDate);
    }

    private void setCheckoutDate(String checkoutDate) {
        webDriver.findElement(checkOutLocator).sendKeys(checkoutDate);
    }

    private void save() {
        webDriver.findElement(saveButtonLocator).click();
    }

    private WebElement getBookingRecord(WebElement bookingId) {
        return (WebElement) ((JavascriptExecutor) webDriver).executeScript(
                "return arguments[0].parentNode.parentNode;", bookingId);
    }

    private WebElement getBookingId(String firstName) {
        By bookingLocator = By.xpath("//p[contains(text(), '" + firstName + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookingLocator));
        return webDriver.findElement(bookingLocator);
    }

    private List<WebElement> xpathContainsTextElements(String value) {
        return webDriver.findElements(By.xpath("//p[contains(text(), '" + value + "')]"));
    }

    private WebElement getBookingColumn(WebElement webElement, String colNo) {
        return webElement.findElement(By.xpath("div[" + colNo + "]/p"));
    }
}
