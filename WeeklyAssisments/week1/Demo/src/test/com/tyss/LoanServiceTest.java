package com.tyss;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;


public class LoanServiceTest {

    private LoanService loanService;

    @BeforeEach
   public  void setup() {
        loanService = new LoanService();
    }


    @Test
    public void testValidEligibility() {
        assertTrue(loanService.isEligible(30, 30000));
    }


    @Test
    public void testInvalidAgeLow() {
        assertFalse(loanService.isEligible(20, 30000));
    }


    @Test
    public void testInvalidAgeHigh() {
        assertFalse(loanService.isEligible(61, 30000));
    }


    @Test
    public void testInvalidSalary() {
        assertFalse(loanService.isEligible(30, 20000));
    }


    @Test
    public  void testBoundaryEligibility() {
        assertAll(
                () -> assertTrue(loanService.isEligible(21, 25000)),
                () -> assertTrue(loanService.isEligible(60, 25000)),
                () -> assertFalse(loanService.isEligible(20, 25000)),
                () -> assertFalse(loanService.isEligible(61, 25000))
        );
    }


    @Test
    void testValidEMI() {
        double emi = loanService.calculateEMI(120000, 2);
        assertEquals(5000.0, emi, 0.001);
    }


    @Test
    public void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.calculateEMI(0, 2);
        });
    }


    @Test
    public void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.calculateEMI(100000, 0);
        });
    }


    @Test
    public void testPremiumCategory() {
        String category = loanService.getLoanCategory(800);
        assertEquals("Premium", category);
        assertNotNull(category);
    }

    // 10. Standard Credit Score
    @Test
    public void testStandardCategory() {
        String category = loanService.getLoanCategory(650);
        assertEquals("Standard", category);
        assertNotNull(category);
    }


    @Test
    public void testHighRiskCategory() {
        String category = loanService.getLoanCategory(500);
        assertEquals("High Risk", category);
        assertNotNull(category);
    }


    @Test
    public void testAllCategories() {

        assertAll("Loan Categories",
                () -> assertEquals("Premium", loanService.getLoanCategory(760)),
                () -> assertEquals("Standard", loanService.getLoanCategory(700)),
                () -> assertEquals("High Risk", loanService.getLoanCategory(400))
        );
    }
}
