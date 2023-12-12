package com.ministryoftesting.e2e.examples.intermediate1;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Screenshotter implements AfterTestExecutionCallback {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        Screenshotter.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws IOException {
        if (context.getExecutionException().isPresent()) {
            Shutterbug.shootPage(driver).withName(context.getDisplayName()).save("target/screenshots/");

            InputStream is = Files.newInputStream(Paths.get("target/screenshots/" + context.getDisplayName() + ".png"));
            Allure.attachment(context.getDisplayName() + ".png", is);
        }
    }

}
