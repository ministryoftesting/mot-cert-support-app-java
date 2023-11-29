// This line specifies the package where this Java class belongs.
package com.ministryoftesting.e2e.examples.module4;

// These are import statements, allowing us to use external classes and libraries in our code.
import com.ministryoftesting.e2e.examples.module5.LoginPage;
import com.ministryoftesting.e2e.examples.module5.ProjectsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// This import statement allows us to use specific methods from the JUnit testing framework.
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This is the declaration of the Java class named 'LoginTest'.
public class LoginTest {

    // This is a method in the class, annotated with @Test, indicating it's a test case.
    @Test
    public void testPageUpdatesToProjectPageAfterLogin() {
        // Set up the WebDriver for Chrome using WebDriverManager.
        WebDriverManager.chromedriver().setup();

        // Initialize a new instance of the ChromeDriver.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        // Open a web page with the given URL in the Chrome browser.
        driver.get("http://localhost:8080");

        driver.findElement(By.name("email")).sendKeys("admin@test.com");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(".card-title")));

        // Assert that the title of the ProjectsPage is equal to the expected value "Projects".
        String title = driver.findElement(By.cssSelector(".card-title")).getText();
        assertEquals("Projects", title);

        // Close the Chrome browser window.
        driver.close();

        // Quit the WebDriver, ending the browser session.
        driver.quit();
    }
}
