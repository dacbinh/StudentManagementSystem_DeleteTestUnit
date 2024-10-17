package org.testing.course;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BlackWhiteBoxCreateUpdateTest {
    private StudentService ss;

    @BeforeEach
    void setUp() {
        ss = new StudentService();
    }

    // Function: Create a Student
    @Test
    void testCreateStudent() {
        // Black Box Testing - Test tạo sinh viên với thông tin hợp lệ
        assertDoesNotThrow(() -> ss.addStudent(new Student("SE12345", "An", "SE1835")));
        assertNotNull(ss.getStudent("SE12345"), "Student should be created and retrievable.");
    }

    @Test
    void testCreateStudentWithEmptyFields() {
        // Black Box Testing - Kiểm tra trường hợp thông tin sinh viên bị thiếu
        assertThrows(InvalidStudentException.class, () -> {
            ss.addStudent(new Student("", "An", "SE1835")); // Mã sinh viên rỗng
        });
    }

    @Test
    void testCreateStudentWithInvalidValues() {
        // Black Box Testing - Kiểm tra với giá trị không hợp lệ
        assertThrows(InvalidStudentException.class, () -> {
            ss.addStudent(new Student("SE12345", "", "SE1835")); // Tên sinh viên rỗng
        });
    }

    @Test
    void testDuplicateStudentID() throws InvalidStudentException {
        // Black Box Testing - Kiểm tra trường hợp mã sinh viên bị trùng
        ss.addStudent(new Student("SE12345", "An", "SE1835"));
        assertThrows(InvalidStudentException.class, () -> {
            ss.addStudent(new Student("SE12345", "Bao", "SE1836")); // Mã sinh viên trùng
        });
    }

    @Test
    void testCreateStudentAllFieldsEmpty() {
        // Black Box Testing - Kiểm tra trường hợp để trống tất cả các trường
        assertThrows(InvalidStudentException.class, () -> {
            ss.addStudent(new Student("", "", "")); // Tất cả trường rỗng
        });
    }

    // Function: Updating a Student information
    @Test
    void testUpdateStudentWithEmptyID() {
        // Black Box Testing - Kiểm tra trường hợp ID sinh viên để trống khi cập nhật
        assertThrows(InvalidStudentException.class, () -> {
            ss.updateStudent("", new Student("SE12345", "Updated", "SE1835"));
        });
    }

    @Test
    void testUpdateNonExistentStudent() {
        // Black Box Testing - Cập nhật sinh viên không tồn tại
        assertThrows(InvalidStudentException.class, () -> {
            ss.updateStudent("NonExistentID", new Student("SE12345", "Updated", "SE1835"));
        });
    }

    @Test
    void testUpdateStudentWithValidInfo() throws InvalidStudentException {
        // White Box Testing - Cập nhật sinh viên với thông tin hợp lệ
        ss.addStudent(new Student("SE12345", "An", "SE1835"));
        assertDoesNotThrow(() -> ss.updateStudent("SE12345", new Student("SE12345", "Updated", "SE1836")));
        assertEquals("Updated", ss.getStudent("SE12345").getStdName(), "Student name should be updated.");
    }

    @Test
    void testUpdateStudentWithEmptyFields() throws InvalidStudentException {
        // White Box Testing - Cập nhật sinh viên với thông tin trống
        ss.addStudent(new Student("SE12345", "An", "SE1835"));
        assertThrows(InvalidStudentException.class, () -> {ss.updateStudent("SE12345", new Student("SE12345", "", "")); // Thông tin trống
        });
    }

    @Test
    void testUpdateStudentWithInvalidValues() throws InvalidStudentException {
        // White Box Testing - Cập nhật sinh viên với giá trị không hợp lệ
        ss.addStudent(new Student("SE12345", "An", "SE1835"));
        assertThrows(InvalidStudentException.class, () -> {
            ss.updateStudent("SE12345", new Student("SE12345", "", "InvalidClass")); // Giá trị không hợp lệ
        });
    }

    // Function: Get Student
    @Test
    void testGetStudentWithNonExistentID() {
        // Black Box Testing - Kiểm tra trường hợp tìm sinh viên không tồn tại
        assertNull(ss.getStudent("NonExistentID"), "Student should not exist.");
    }

    @Test
    void testGetStudentWithValidID() throws InvalidStudentException {
        // Black Box Testing - Kiểm tra lấy thông tin sinh viên hợp lệ
        ss.addStudent(new Student("SE12345", "An", "SE1835"));
        Student retrievedStudent = ss.getStudent("SE12345");
        assertNotNull(retrievedStudent);
        assertEquals("An", retrievedStudent.getStdName(), "Student name should match.");
    }
}