package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double a = 22;
    	int b = 15;
    	int expected = 330;
    	
        //when
    	double actual = payroll.calculatePaycheck(a, b);
    	
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int a = 325;
    	int expected = 186;

        //when
    	int actual = (int) payroll.calculateMileageReimbursement(a);
    	
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String a = "Lucas";
    	double b = 14.75;
    	String expected = "Hello Lucas, We are pleased to offer you an hourly wage of 14.75";
        //when
    	String actual = payroll.createOfferLetter(a, b);

        //then
    	assertEquals(expected, actual);
    }

}