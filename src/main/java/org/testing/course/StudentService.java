package org.testing.course;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> dataMap = new HashMap<>();

    public void addStudent(Student s) throws InvalidStudentException {
        if (dataMap.containsKey(s.getStdId())) throw new InvalidStudentException();
        this.dataMap.put(s.getStdId(), s);
    }

    public Student getStudent(String stdId) {
        return this.dataMap.get(stdId);
    }

    public void deleteStudent(String stdId) throws InvalidStudentException {
        if (!dataMap.containsKey(stdId)) throw new InvalidStudentException();
        this.dataMap.remove(stdId);
    }

    public void updateStudent(String stdId, Student s) throws InvalidStudentException {
        if (!dataMap.containsKey(stdId)) throw new InvalidStudentException();
        this.dataMap.put(stdId, s);
    }

}
