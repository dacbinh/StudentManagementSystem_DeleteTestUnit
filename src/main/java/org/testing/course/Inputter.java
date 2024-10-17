package org.testing.course;

import java.util.Scanner;

public class Inputter {

    public final static Scanner sc = new Scanner(System.in);

    public static int inputInt(int min, int max) {
        try {
            final int n = inputInt();
            if (n >= min && n <= max) {
                return n;
            } else {
                throw new NumberOutOfRangeException();
            }
        } catch (NumberOutOfRangeException e) {
            System.out.println("Number is out of range, try again.");
            return inputInt(min, max);
        }
    }

    public static int inputIntMin(int min) {
        try {
            final int n = inputInt();
            if (n >= min) {
                return n;
            } else throw new NumberOutOfRangeException();
        } catch (NumberOutOfRangeException e) {
            System.out.println("Number out of range, try again");
            return inputIntMin(min);
        }
    }

    public static double inputDoubleMin(double min) {
        try {
            final double n = inputDouble();
            if (n >= min) {
                return n;
            } else throw new NumberOutOfRangeException();
        } catch (NumberOutOfRangeException e) {
            System.out.println("Number out of range, try again");
            return inputDoubleMin(min);
        }
    }

    public static int inputInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            return inputInt();
        }
    }

    public static int inputInt(String message) {
        System.out.println(message);
        return inputInt();
    }


    public static double inputDouble() {
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            return inputDouble();
        }
    }

    public static String inputString() {
        try {
            return sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return inputString();
        }
    }

    public static String inputNonEmptyString() {
        try {
            String str = inputString();
            if (str.isEmpty()) throw new EmptyStringException();
            return str;
        } catch (EmptyStringException e) {
            System.out.println("String is empty, try again;");
            return inputNonEmptyString();
        }
    }

    public static String inputNonEmptyString(String message) {
        System.out.println(message);
        return inputNonEmptyString();
    }

    public static String inputByPattern(String message, String pattern) {
        System.out.println(message);
        try {
            String str = inputNonEmptyString("Enter the code:");
            if (str.matches(pattern)) {
                return str;
            } else throw new InvalidCodeException();
        } catch (Exception e) {
            System.out.println(e.toString());
            return inputByPattern(message, pattern);
        }
    }

    public static boolean inputBoolean() {
        String inp = inputNonEmptyString();
        if (!inp.equals("true") && !inp.equals("false")) {
            System.out.println("Please enter true/false");
            return inputBoolean();
        } else return Boolean.parseBoolean(inp);
    }
    public static Student inputStudent() {
        String stdId;
        String stdName;
        String stdClass;
        while (true) {
            var str = Inputter.inputString();
            if (Student.verifyID(str)) {
                stdId = str;
                break;
            }
            System.out.println("Invalid Student ID, try again");
        }
        while (true) {
            var str = Inputter.inputString();
            if (Student.verifyName(str)) {
                stdName = str;
                break;
            }
            System.out.println("Invalid Student Name, try again");
        }
        while (true) {
            var str = Inputter.inputString();
            if (Student.verifyClass(str)) {
                stdClass = str;
                break;
            }
            System.out.println("Invalid Student Name, try again");
        }
        return new Student(stdId, stdName, stdClass);
    }
}

class NumberOutOfRangeException extends Exception {

}

class EmptyStringException extends Exception {


}

class InvalidCodeException extends Exception {

}