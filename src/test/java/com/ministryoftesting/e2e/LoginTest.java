// This line specifies the package where this Java class belongs.
package com.ministryoftesting.e2e;

// These are import statements, allowing us to use external classes and libraries in our code.
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// This import statement allows us to use specific methods from the JUnit testing framework.
import static org.junit.jupiter.api.Assertions.assertEquals;

// This is the declaration of the Java class named 'LoginTest'.
public class LoginTest {

    // This is a method in the class, annotated with @Test, indicating it's a test case.
    @Test
    public void testPageUpdatesToProjectPageAfterLogin() {
        // Set up the WebDriver for Chrome using WebDriverManager.
        WebDriverManager.chromedriver().setup();

        // Initialize a new instance of the ChromeDriver.
        WebDriver driver = new ChromeDriver();

        // Open a web page with the given URL in the Chrome browser.
        driver.get("http://localhost:8080");

        // Create an instance of the LoginPage class, which is a custom class.
        LoginPage loginPage = new LoginPage(driver);

        // Perform actions on the login page: sending email, password, and submitting the form.
        loginPage.sendEmail("admin@test.com");
        loginPage.sendPassword("password123");
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
