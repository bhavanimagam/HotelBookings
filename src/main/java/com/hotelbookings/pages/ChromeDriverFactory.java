package com.hotelbookings.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static WebDriver getWebDriver() {
        if (isWindows()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driverexecutables/chromedriver.exe");
        } else if (isMac()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driverexecutables/chromedriver-mac");
        } else if (isUnix()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "driverexecutables/chromedriver-linux");
        } else {
            throw new UnsupportedOperationException("Operating system is not supported");
        }
        return new ChromeDriver();
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }
}
