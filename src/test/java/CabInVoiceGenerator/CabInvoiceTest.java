package CabInVoiceGenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {

	CabInvoiceMain cabInvoiceMain;
	RidesRepository ridesRepository;

	@Before
	public void createObject() {
		ridesRepository = new RidesRepository();
		cabInvoiceMain = new CabInvoiceMain(ridesRepository);
		Ride[] ridesArray= {new Ride(5.0,5,CabRideEnum.NORMAL),new Ride(0.2,2,CabRideEnum.NORMAL)};	
		cabInvoiceMain.addRidesToRepo(1,ridesArray);		
		Ride[] ridesArray21 = {new Ride(1.0,5,CabRideEnum.PREMIMUM)};
		cabInvoiceMain.addRidesToRepo(2,ridesArray21);
		Ride[] ridesArray22 = {new Ride(0.2,2,CabRideEnum.PREMIMUM),new Ride(2.0,5,CabRideEnum.PREMIMUM)};
		cabInvoiceMain.addRidesToRepo(2,ridesArray22);	
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
		Ride[] rides = {new Ride(5.0,5,CabRideEnum.NORMAL),new Ride(0.1,2,CabRideEnum.NORMAL)};
		double totalFare = cabInvoiceMain.calculateTotalFare(rides);
		assertEquals(60.0, totalFare, 0.0);
	}
	
	@Test
	public void givenDistanceAndTimeForMultipleridesShouldReturnSummary() {
		Ride[] rides = {new Ride(5.0,5,CabRideEnum.NORMAL),new Ride(0.1,2,CabRideEnum.NORMAL)};
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(2,60.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateSummary(rides);
		assertEquals(ecpectedInvoice, outputInvoice);
	}
	
	@Test
	public void givenUserIdShouldReturnSummary() {
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(2,60.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateInvoice(1);
		assertEquals(ecpectedInvoice, outputInvoice);
	}
	
	@Test
	public void givenPremimumTypeAndUserIdShouldReturnSummary() {
		InvoiceSummary ecpectedInvoice = new InvoiceSummary(3,85.0);
		InvoiceSummary outputInvoice = cabInvoiceMain.generateInvoice(2);
		assertEquals(ecpectedInvoice, outputInvoice);
	}

}
