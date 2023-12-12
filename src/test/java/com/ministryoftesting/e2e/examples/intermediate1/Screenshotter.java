package com.ministryoftesting.e2e.examples.intermediate1;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class Screenshotter implements AfterTestExecutionCallback {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        Screenshotter.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            Shutterbug.shootPage(driver).save();
        }
    }

}
