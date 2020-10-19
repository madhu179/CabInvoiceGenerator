package CabInVoiceGenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import CabInVoiceGenerator.CabInvoiceMain.Subscription_Type;

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
		double fare = cabInvoiceMain.calculateFare(distance, time,Subscription_Type.NORMAL);
		assertEquals(55.0, fare, 0.0);
	}
	
	@Test
	public void givenLowDistanceAndTimeShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 2;
		double fare = cabInvoiceMain.calculateFare(distance, time,Subscription_Type.NORMAL);
		assertEquals(5.0, fare, 0.0);
	}
	
	@Test
	public void givenDistanceAndTimeForMultipleridesShouldReturnFare() {
		Ride[] rides = {new Ride(5.0,5),new Ride(0.1,2)};
		double totalFare = cabInvoiceMain.calculateTotalFare(rides,Subscription_Type.NORMAL);
		assertEquals(60.0, totalFare, 0.0);
	}
	
	@Test
	public void givenDistanceAndTimeForMultipleridesShouldReturnSummary() {
		Ride[] rides = {new Ride(5.0,5),new Ride(0.1,2)};
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(2,60.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateSummary(rides,Subscription_Type.NORMAL);
		assertEquals(ecpectedInvoice, outputInvoice);
	}
	
	@Test
	public void givenUserIdShouldReturnSummary() {
		Ride[][] ridesArray= {{new Ride(5.0,5),new Ride(0.1,2)},{new Ride(1.0,5),new Ride(0.2,2),new Ride(2.0,5)}};
		int[] userArray = {1,2};
		cabInvoiceMain.addRidesToRepo(userArray,ridesArray);
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(3,45.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateInvoice(2,Subscription_Type.NORMAL);
		assertEquals(ecpectedInvoice, outputInvoice);
	}
	
	@Test
	public void givenPremimumTypeAndUserIdShouldReturnSummary() {
		Ride[][] ridesArray= {{new Ride(5.0,5),new Ride(0.2,2)},{new Ride(1.0,5),new Ride(0.2,2),new Ride(2.0,5)}};
		int[] userArray = {1,2};
		cabInvoiceMain.addRidesToRepo(userArray,ridesArray);
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(2,105.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateInvoice(1,Subscription_Type.PREMIMUM);
		assertEquals(ecpectedInvoice, outputInvoice);
	}

}
