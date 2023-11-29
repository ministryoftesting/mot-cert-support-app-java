// Define the Java package where this class belongs
package com.ministryoftesting.e2e.examples.module5;

// Import required Selenium libraries
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Create a class named 'LoginPage'
public class LoginPage {

    // Define WebElements for email, password, and submit button
    @FindBy(how = How.NAME, using = "email")
    private WebElement email;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.CSS, using = "button")
    private WebElement submit;

    // Store a reference to the WebDriver instance
    private WebDriver driver;

    // Constructor for the LoginPage class, accepting a WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Initialize elements using PageFactory
        PageFactory.initElements(driver, this);

        // Create a WebDriverWait to wait for a specific element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button")));
    }

    // Method to input an email address into the email field
    public void sendEmail(String value) {
        email.sendKeys(value);
    }

    // Method to input a password into the password field
    public void sendPassword(String value) {
        password.sendKeys(value);
    }

    // Method to submit the login form
    public void submitForm() {
        submit.click();
    }
}
