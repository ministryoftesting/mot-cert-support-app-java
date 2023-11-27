// This line indicates that we're placing our code in a specific package called "com.ministryoftesting.unit.examples."
package com.ministryoftesting.unit.examples.module4;

// We're importing a class called "Test" from the "org.junit.jupiter.api" package.
import org.junit.jupiter.api.Test;

// We're defining a Java class named "ExampleTest."
public class ExampleTest {

    // This is a Java annotation that marks a method as a test case.
    @Test
    public void shellTest() {
        // This line prints a message to the console.
        System.out.println("This is a shell test");
    }
}
