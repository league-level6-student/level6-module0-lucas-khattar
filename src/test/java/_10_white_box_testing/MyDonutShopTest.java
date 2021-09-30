package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;

    @Mock
    BakeryService bakeryService;
    
    @Mock
    Order order;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	when(paymentService.charge(order)).thenReturn(true);
    	when(order.isDelivery()).thenReturn(true);

        //when
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	when(bakeryService.getDonutsRemaining()).thenReturn(9);
    	when(order.getNumberOfDonuts()).thenReturn(12);
        //when
    	myDonutShop.openForTheDay();
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
    	assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
    	verify(paymentService, never()).charge(order);
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given   	
        //when
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
    	assertEquals(exceptionThrown.getMessage(), "Sorry we're currently closed");
    	verify(paymentService, never()).charge(order);
    }

}