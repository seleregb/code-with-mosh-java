package com.sammy.oop;

public class Main {

    public static void main(String[] args) {
        var employee = new Employee(50_000, 20);
        int wage = employee.calculateWage(10);
        employee.printNumberOfEmployees();
	    // write your code here
        System.out.println(wage);
    }
}
