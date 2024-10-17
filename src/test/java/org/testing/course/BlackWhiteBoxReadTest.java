package org.testing.course;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BlackWhiteBoxReadTest {

    StudentService ss;

    @BeforeEach
    public void setUp() {
        ss = new StudentService();
        try {
            ss.addStudent(new Student("SE12345", "An", "SE1835"));
            ss.addStudent(new Student("SE12346", "Bao", "SE1836"));
        } catch (InvalidStudentException e) {
            fail("Setup failed due to: " + e.getMessage());
        }
    }

    @Nested
    class WhiteBoxTest {

        @Test
        void readExistingStudentShouldReturnCorrectStudentObject() {
            Student s = ss.getStudent("SE12345");
            assertNotNull(s, "Expected student to be found");
            assertEquals("SE12345", s.getStdId(), "Expected correct student ID");
            assertEquals("An", s.getStdName(), "Expected correct student name");
            assertEquals("SE1835", s.getStdClass(), "Expected correct student class");
        }

        @Test
        void readNonExistentStudentShouldReturnNull() {
            Student s = ss.getStudent("SE99999");
            assertNull(s, "Expected student to not be found");
        }
    }

    @Nested
    class BlackBoxTest {

        @Test
        void readSuccessShouldReturnStudentObject() {
            Student s = ss.getStudent("SE12346");
            assertNotNull(s, "Student found");
            assertEquals("Bao", s.getStdName(), "Student name is Bao");
            assertEquals("SE1836", s.getStdClass(), "Class is SE1836");
        }

        @Test
        void readFailureShouldReturnErrorMessage() {
            Student s = ss.getStudent("S2345");
            assertNull(s, "No student found with this ID");
        }
    }
}