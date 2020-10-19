package CabInVoiceGenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {

	CabInvoiceMain cabInvoiceMain;

	@Before
	public void createObject() {
		cabInvoiceMain = new CabInvoiceMain();
	}

	@Test
	public void givenDistanceAndTimeShouldReturnFare() {
		double distance = 5;
		int time = 5;
		double fare = cabInvoiceMain.calculateFare(distance, time);
		assertEquals(55.0, fare, 0.0);
	}
	
	@Test
	public void givenLowDistanceAndTimeShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 2;
		double fare = cabInvoiceMain.calculateFare(distance, time);
		assertEquals(5.0, fare, 0.0);
	}
	
	@Test
	public void givenDistanceAndTimeForMultipleridesShouldReturnFare() {
		Ride[] rides = {new Ride(5.0,5),new Ride(0.1,2)};
		double totalFare = cabInvoiceMain.calculateTotalFare(rides);
		assertEquals(60.0, totalFare, 0.0);
	}

}
