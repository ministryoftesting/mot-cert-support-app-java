package com.ministryoftesting.e2e.examples.module5;

import com.ministryoftesting.e2e.examples.LoginPage;
import com.ministryoftesting.e2e.examples.ProjectsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    public void testStandardUserCanLogin() {
        DataBuilder dataBuilder = new DataBuilder();
        TimesheetCredential credentials = dataBuilder.getUserCredentials("user");

        // Set up the WebDriver for Chrome using WebDriverManager.
        WebDriverManager.chromedriver().setup();

        // Initialize a new instance of the ChromeDriver.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        // Open a web page with the given URL in the Chrome browser.
        driver.get("http://localhost:8080");

        // Create an instance of the LoginPage class, which is a custom class.
        LoginPage loginPage = new LoginPage(driver);

        // Perform actions on the login page: sending email, password, and submitting the form.
        loginPage.sendEmail(credentials.getEmail());
        loginPage.sendPassword(credentials.getPassword());
        loginPage.submitForm();

        // Create an instance of the ProjectsPage class.
        ProjectsPage projectsPage = new ProjectsPage(driver);

        // Get the title of the ProjectsPage and store it in the 'title' variable.
        String title = projectsPage.getTitle();

        // Assert that the title of the ProjectsPage is equal to the expected value "Projects".
        assertEquals("Projects", title);

        // Close the Chrome browser window.
        driver.close();

        // Quit the WebDriver, ending the browser session.
        driver.quit();
    }

}
