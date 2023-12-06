//package com.ministryoftesting.e2e.examples;
//
//import com.ministryoftesting.e2e.examples.intermediate1.LoginPage;
//import com.ministryoftesting.e2e.examples.intermediate1.ProjectsPage;
//import com.ministryoftesting.models.auth.Credentials;
//import com.ministryoftesting.models.user.User;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FailingCheckExample {
//
//    @Test
//    public void exampleCheck(){
//        Credentials credentials = given()
//                .body("{\"email\":\"user@test.com\",\"password\":\"password123\"}")
//                .contentType("application/json")
//                .post("/v1/auth/login")
//                .as(Credentials.class);
//
//        String token = credentials.getToken();
//
//        User user = given()
//                .body(new User("Jon", "test@email.com", "password123", "admin"))
//                .contentType("application/json")
//                .header("Authorization", "Bearer " + token)
//                .post("/v1/user")
//                .as(User.class);
//
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        WebDriver driver = new ChromeDriver(options);
//
//        driver.get("http://localhost:8080");
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.sendEmail(user.getEmail());
//        loginPage.sendPassword(user.getPassword());
//        loginPage.submitForm();
//
//        // Create an instance of the ProjectsPage class.
//        ProjectsPage projectsPage = new ProjectsPage(driver);
//
//        String title = projectsPage.getTitle();
//
//        assertEquals("Projects", title);
//
//        driver.close();
//        driver.quit();
//    }
//
//}
