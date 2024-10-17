package org.testing.course;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FieldValidationWhiteBoxTest {

    StudentService ss;
    Student s;
    @BeforeAll
    static void printMessage() {
        System.out.println("Field Validation");
        System.out.println("This test depends on ID, Name and Class.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Getting ready to test this module..");
        ss = new StudentService();
    }

    @AfterEach
    void tearDown() {
        System.out.println("All tests are completed.");
    }

    @Test
    void emptyStringShouldThrowEmptyStringException() {
        assertFalse(Student.verifyID(""));
        assertFalse(Student.verifyName(""));
        assertFalse(Student.verifyClass(""));
    }

    @Test
    void invalidIDShouldThrowInvalidIDException() {
        assertFalse(Student.verifyID("123456"));
    }

    @Test
    void validIDShouldBePossible() {
        assertTrue(Student.verifyID("SE196412"));
    }

    @Test
    void specialCharactersShouldBeInvalid() {
        assertFalse(Student.verifyName("@abc%!@2@##$"));
    }

    @Test
    void moreThan50CharactersShouldBeInvalid() {
        assertFalse(Student.verifyName("50 alphabetical characters"));
    }

    @Test
    void validNameShouldBePossible() {
        assertTrue(Student.verifyName("Nguyen Van A"));
    }

    @Test
    void invalidClassShouldThrowInvalidClassException() {
        assertFalse(Student.verifyClass("Class@123"));
    }

    @Test
    void validClassShouldBePossible() {
        assertTrue(Student.verifyClass("SE1837"));
    }
}
