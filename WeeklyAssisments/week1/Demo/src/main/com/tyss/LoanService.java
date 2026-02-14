package com.tyss;

//ASSESSMENT 1: JUnit Testing (Plain Java Project)
//Objective
//Write basic unit tests using JUnit 5 and different assertion types.
//________________________________________
//Case Study: Simple Loan Service
//Create a Java class named:
//LoanService
//Implement the following methods:
//        1.	boolean isEligible(int age, double salary)
//2.	double calculateEMI(double loanAmount, int tenureYears)
//3.	String getLoanCategory(int creditScore)
//________________________________________
//Functional Requirements
//isEligible()
//●	Age must be between 21 and 60 (inclusive).
//        ●	Salary must be greater than or equal to 25,000.
//        ●	Return true if eligible; otherwise return false.
//calculateEMI()
//●	EMI = loanAmount / (tenureYears * 12)
//        ●	If loanAmount <= 0 → throw IllegalArgumentException.
//●	If tenureYears <= 0 → throw IllegalArgumentException.
//getLoanCategory()
//●	creditScore >= 750 → "Premium"
//        ●	creditScore between 600 and 749 → "Standard"
//        ●	creditScore < 600 → "High Risk"
//________________________________________
//Testing Instructions
//Create a test class named:
//LoanServiceTest
//Requirements:
//        ●	Write minimum 10 test cases.
//        ●	Use the following assertions:
//        ○	assertEquals
//○	assertTrue
//○	assertFalse
//○	assertThrows
//○	assertNotNull
//○	assertAll
//Test cases must include:
//        ●	Valid eligibility
//●	Invalid age
//●	Invalid salary
//●	Valid EMI calculation
//●	Exception for invalid loan amount
//●	Exception for invalid tenure
//●	All credit score categories
//●	Boundary values
//●	Grouped assertions using assertAll
//Constraints:
//        ●	Plain Java Project (No Maven required)
//●	Use JUnit 5 only
//Deliverables:
//        ●	LoanService.java
//●	LoanServiceTest.java
//●	Screenshot of successful test execution
//        ________________________________________



public class LoanService {

    public boolean isEligible(int age, double salary) {

        if (age >= 21 && age <= 60 && salary >= 25000) {
            return true;
        }
        return false;
    }


    public double calculateEMI(double loanAmount, int tenureYears) {

        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }

        if (tenureYears <= 0) {
            throw new IllegalArgumentException("Tenure must be positive");
        }

        return loanAmount / (tenureYears * 12);
    }


    public String getLoanCategory(int creditScore) {

        if (creditScore >= 750) {
            return "Premium";
        } else if (creditScore >= 600) {
            return "Standard";
        } else {
            return "High Risk";
        }
    }
}
