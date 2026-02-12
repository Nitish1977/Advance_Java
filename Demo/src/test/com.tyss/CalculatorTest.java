package com.tyss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator c = new Calculator();


    @Test
    public void testAdd() {

        assertEquals(5, c.add(2, 3));
    }
    @Test
    public void testSubtract(){

        assertEquals(3,c.subtract(5,2));
    }
    @Test
    public void testIsEven(){

        assertTrue(c.isEven(4));

        assertTrue(c.isEven(5),"you are passing odd number");


    }

    @Test
    public void testDivide(){

        assertEquals(2, c.divide(10,5));
    }
    @Test
    public void testReminder(){

        assertEquals(2,c.reminder(8,3));

    }
    @ParameterizedTest
    @CsvSource({
        "2,3,5",
            "0,0,0",
            "100,200,300"
    })

    public void testAddParameterized(int a, int b , int excepted) {

    assertEquals(excepted, c.add(a, b));
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,7,-9,-2})
    public void testIsEvenReturnsTrue(int number){
        assertTrue(c.isEven(number));
    }

    @ParameterizedTest
    @MethodSource("provideDivisionTestCases")
    public void testDivideWithMethodSource(int a, int b, int excepted){
        assertEquals(excepted, c.divide(a,b));
    }

    private static Stream<Arguments> provideDivisionTestCases(){
        return  Stream.of(
            Arguments.of(20,4,5),
                Arguments.of(15,5,3),
                Arguments.of(0,7,0),
                Arguments.of(100,25,4)

        );
    }
    @ParameterizedTest
    @CsvSource({
            "0,1",
            "1,1",
            "5, 120",
            "3,60",
            "4,24"
    })

    public  void testFactorialParameterized(int input, int expected){
        assertEquals(expected, c.factorial(input));
    }


    @ParameterizedTest
    @CsvFileSource(files = "test-data/add.csv", numLinesToSkip = 1)
    public  void simpleAddTest(int a, int b, int expected){
        System.out.println("Simple @CSVFILESOURCE test: "+a+" + "+b);
        assertEquals(expected, c.add(a,b));
    }





}
