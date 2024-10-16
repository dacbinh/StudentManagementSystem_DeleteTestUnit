package org.testing.course;

public class Student {
    private final String stdId;
    private String stdName;
    private String stdClass;

    public String getStdId() {
        return stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdClass() {
        return stdClass;
    }

    public void setStdClass(String stdClass) {
        this.stdClass = stdClass;
    }

    public Student(String stdId, String stdName, String stdClass) {
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdClass = stdClass;
    }

    public static boolean verifyID(String ID){
        return true;
    }
    public static boolean verifyName(String name){
        return true;
    }

    public static boolean verifyClass(String stdClass){
        return false;
    }
}
