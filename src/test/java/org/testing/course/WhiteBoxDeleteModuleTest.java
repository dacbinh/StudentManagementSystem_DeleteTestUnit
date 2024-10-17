package org.testing.course;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class WhiteBoxDeleteModuleTest {
    StudentService ss;

    @BeforeAll
    static void printMessage() {
        System.out.println("Unit test for Student delete functions");
        System.out.println("This test depends on ID and valid student test, assuming they are all passed and students are correctly defined");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Getting ready to test this module...");
        ss = new StudentService();
        try {
            ss.addStudent(new Student("SE12345", "An", "SE1835"));
            ss.addStudent(new Student("SE12346", "Bao", "SE1836"));
        } catch (InvalidStudentException ivs) {
        }
    }

    @AfterAll
    static void showEndingMessage() {
        System.out.println("All test are completed");
    }

    @Test
    void emptyIDShouldThrowEmptyStringException() {
        assertThrows(EmptyStringException.class, () -> ss.deleteStudent(""));
    }

    @Test
    void notExistIDShouldThrowInvalidStudentException() {
        assertThrows(InvalidStudentException.class, () -> ss.deleteStudent("abcxys"), "This ID doesn't exist");
    }

    @Test
    void existStudentShouldNotThrowInvalidStudentException() {
        assertDoesNotThrow(() -> ss.deleteStudent("SE12345"));
    }

    @Test
    void deleteExistStudentShouldBePossible() {
        assertDoesNotThrow(() -> ss.deleteStudent("SE12345"));
        assertNull(ss.getStudent("SE12345"));
    }

    @Test
    void otherStudentShouldNotBeRemovedAfterTheChosenOneWas() {
        try {
            ss.deleteStudent("SE12345");
        } catch (InvalidStudentException ivs) {
            fail("detected unexpected throw of" + ivs.getLocalizedMessage());
        }
        assertNotEquals(null, ss.getStudent("SE12346"));
    }

    @Test
    void deletedStudentShouldNotStillExist() {
        try {
            ss.deleteStudent("SE12345");
        } catch (InvalidStudentException ivs) {
            fail("detected unexpected throw of" + ivs.getLocalizedMessage());
        }
        assertEquals(null, ss.getStudent("SE12345"));
    }

    @Test
    void doubleDeleteAStudentShouldThrowInvalidStudentException() {
        try {
            ss.deleteStudent("SE12345");
        } catch (InvalidStudentException ivs) {
            fail("detected unexpected throw of " + ivs.getLocalizedMessage());
        }
        assertThrows(InvalidStudentException.class, () -> ss.deleteStudent("SE12345"));
    }


    @Nested
    class BlackBoxDeleteModuleTest {
        @BeforeAll
        static void printMainTestMess() {
            System.out.println("Testing main");
            System.out.println("Creating new method to simulate Main running, focussing on function no.4, all other are dummy function call ");

        }

        String simulateMain(int testingCases, String id) {

            switch (testingCases) {
                case 1:
                    return "Job 1";
                case 2:
                    return "Job 2";
                case 3:
                    return "Job 3";
                case 4:
                    try {
                        ss.deleteStudent(id);
                        return "Deleted student";
                    } catch (InvalidStudentException ivs) {
                        return "Invalid student";
                    }
                default:
                    return "Exit";

            }
        }

        @Test
        void number4WillExecuteDeleteFunction() {
            assertEquals("Deleted student", simulateMain(4, "SE12345"));
        }

        @Test
        void number3WillNotExecuteDeleteFunction() {
            assertNotEquals("Deleted student", simulateMain(3, "SE12345"));
        }

        @Test
        void number2WillNotExecuteDeleteFunction() {
            assertNotEquals("Deleted student", simulateMain(2, "SE12345"));
        }

        @Test
        void number1WillNotExecuteDeleteFunction() {
            assertNotEquals("Deleted student", simulateMain(1, "SE12345"));
        }

        @Test
        void otherNumberWillNotExecuteDeleteFunction() {
            assertNotEquals("Deleted Student", simulateMain(0, "SE12345"), "lowest allowed int value");
            assertNotEquals("Deleted student", simulateMain(5, "SE12345"), "int larger than max choice");
        }

        @Test
        void userShouldSeeMessageForNotExistStudentIDEntered() {
            assertEquals("Invalid student", simulateMain(4, "S2345"));
        }

        @Test
        void deleteShouldBeSuccessForValidID() {
            assertEquals("Deleted student", simulateMain(4, "SE12345"));
        }

        @Test
        void emptyIDSearchShouldShowMessage() {
            assertEquals("Invalid student", simulateMain(4, ""));
        }

        @Test
        void notExistIDShouldReturnNull() {
            assertNull(ss.getStudent("SE123457"));
        }

        @Test
        void existIDShouldReturnAValidStudent() {
            assertNotNull(ss.getStudent("SE12345"), "this Student is in data Map");
        }

    }
}
