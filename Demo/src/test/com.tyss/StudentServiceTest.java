package com.tyss;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    @Test
    public void testStudentService(){
        StudentService s = new StudentService();
        assertTrue(s.eligibity(19));
        assertFalse(s.eligibity(15));
    }

    @Test
    public  void test() {

        assertTrue(10 > 5);
        assertFalse("java".isEmpty());
        assertEquals(4, 2 + 2);
        assertNotEquals(3, 2 + 2);

        String str = "JUnit";
        assertNotNull(str);

        String s1 = "abc";
        String s2 = s1;
        assertSame(s1, s2);

        assertNotSame(new String("abc"), new String("abc"));

        assertAll("multiple checks",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(5 > 1)
        );

        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("notANumber");
        });
    }



}
