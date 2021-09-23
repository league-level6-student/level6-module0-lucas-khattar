package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    Car car;
    
    @Mock
    CellPhone cellPhone;
    
    String name;
    
    String phoneNumber;
    
    int octaneGrade;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver(name, car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedTimeWaste = true;
        //when
    	when(cellPhone.browseCatMemes()).thenReturn(true);
    	boolean actualWasteTime = deliveryDriver.wasteTime();
        //then
    	assertEquals(expectedTimeWaste, actualWasteTime);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expectedRefil = true;
        //when
    	when(car.fillTank(octaneGrade)).thenReturn(true);
    	boolean actualRefil = deliveryDriver.refuel(octaneGrade);
        //then
    	assertEquals(expectedRefil, actualRefil);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expectedCall = true;
        //when
    	when(cellPhone.call(phoneNumber)).thenReturn(true);
    	boolean actualCall = deliveryDriver.contactCustomer(phoneNumber);
        //then
    	assertEquals(expectedCall, actualCall);
    }

}