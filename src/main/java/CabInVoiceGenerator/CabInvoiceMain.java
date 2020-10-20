package CabInVoiceGenerator;

public class CabInvoiceMain {
	private double COST_PER_KM = 10.0;
	private int COST_PER_MINUTE = 1;
	private double MINIMUM_FARE = 5.0;
	
	RidesRepository ridesRepo;
	
	public CabInvoiceMain(RidesRepository ridesRepo)
	{
		this.ridesRepo  = ridesRepo;
	}

	public double calculateFare(double distance, int time) 
	{	
		double fare = COST_PER_KM * distance + COST_PER_MINUTE * time;
		return fare<MINIMUM_FARE ? MINIMUM_FARE : fare;
	}


	public double calculateTotalFare(Ride[] rides) {
		double totalFare = 0;
		for(Ride ride : rides)
		{
			totalFare = totalFare + this.calculateFare(ride.distance, ride.time);
		}
		return totalFare;
	}
	
	public double calculateTotalFareEnum(Ride[] rides) {
		double totalFare = 0;
		for(Ride ride : rides)
		{
			totalFare = totalFare + ride.cabRideType.calculateFareForRide(ride);
		}
		return totalFare;
	}

	public InvoiceSummary generateSummary(Ride[] rides) {
		double totalFare = calculateTotalFareEnum(rides);		
		return new InvoiceSummary(rides.length,totalFare);
	}

	public InvoiceSummary generateInvoice(int userId) {
		Ride[] rides = (Ride[]) ridesRepo.getRideArray(userId);
		return generateSummary(rides);
	}

	public void addRidesToRepo(int userId, Ride[] ridesArray) {
		ridesRepo.addRidesToMap(userId,ridesArray);
	}
}
