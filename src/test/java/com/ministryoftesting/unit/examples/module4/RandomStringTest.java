// This line defines the package name, which organizes code into a specific directory structure.
package com.ministryoftesting.unit.examples.module4;

// These lines import necessary libraries and classes for this Java program.
import com.ministryoftesting.service.RandomString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// This defines a Java class named "RandomStringTest."
public class RandomStringTest {

    // This is a test method named "testRandomStringCanBeCreated."
    // It's marked with the @Test annotation, indicating it's a JUnit test.
    @Test
    public void testRandomStringCanBeCreated() {
        // Create an instance of the RandomString class with a length of 10 characters.
        RandomString randomString = new RandomString(10);

        // Generate a random string with the specified length and store it in "createdString."
        String createdString = randomString.nextString();

        // Perform an assertion to check if the length of the "createdString" is equal to 10.
        // If it's not, the test will fail.
        assertEquals(10, createdString.length());
    }
}
