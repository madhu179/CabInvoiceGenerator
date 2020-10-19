package CabInVoiceGenerator;

public class CabInvoiceMain {
	private double COST_PER_KM = 10.0;
	private int COST_PER_MINUTE = 1;
	private double MINIMUM_FARE = 5.0;
	
	RidesRepository ridesRepo;
	
	public CabInvoiceMain()
	{
		ridesRepo = new RidesRepository();
	}

	public double calculateFare(double distance, int time) 
	{
		double fare = COST_PER_KM * distance + COST_PER_MINUTE * time;
		return fare<MINIMUM_FARE ? MINIMUM_FARE : fare ;
	}

	public double calculateTotalFare(Ride[] rides) {
		double totalFare = 0;
		for(Ride ride : rides)
		{
			totalFare = totalFare + this.calculateFare(ride.distance, ride.time);
		}
		return totalFare;
	}

	public InvoiceSummary generateSummary(Ride[] rides) {
		double totalFare = calculateTotalFare(rides);		
		return new InvoiceSummary(rides.length,totalFare);
	}

	public InvoiceSummary generateInvoice(int userId) {
		Ride[] rides = ridesRepo.getRideArray(userId);
		return generateSummary(rides);
	}

	public void addRidesToRepo(int[] userArray, Ride[][] ridesArray) {
		ridesRepo.addRidesToMap(userArray,ridesArray);
	}
}
