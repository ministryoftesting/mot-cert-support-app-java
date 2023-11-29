// This line specifies the package name for this Java class.
package com.ministryoftesting.e2e.examples.module5;

// Import statements that bring in external libraries for the code to use.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Declaration of the "ProjectsPage" class.
public class ProjectsPage {

    // Annotating a private WebElement field named "title" that represents an HTML element.
    @FindBy(how = How.CSS, using = ".card-title")
    private WebElement title;

    // Constructor for the "ProjectsPage" class, which takes a WebDriver as a parameter.
    public ProjectsPage(WebDriver driver) {
        // Initialize elements on the web page using the PageFactory pattern.
        PageFactory.initElements(driver, this);

        // Create a WebDriverWait instance to wait for a specific condition on the web page.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until an element with the CSS selector ".card-title" becomes visible on the page.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-title")));
    }

    // A public method named "getTitle" that returns the text of the "title" WebElement.
    public String getTitle() {
        return title.getText();
    }
}
