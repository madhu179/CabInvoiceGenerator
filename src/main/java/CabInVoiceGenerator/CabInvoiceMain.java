package CabInVoiceGenerator;

public class CabInvoiceMain {
	private double COST_PER_KM = 10.0;
	private int COST_PER_MINUTE = 1;
	private double MINIMUM_FARE = 5.0;
	
	private double COST_PER_KM_PREMIMUM = 15.0;
	private int COST_PER_MINUTE_PREMIMUM = 2;
	private double MINIMUM_FARE_PREMIMUM = 20.0;
	
	public enum Subscription_Type
	{
		NORMAL,
		PREMIMUM
	}
	Subscription_Type Type;
	
	RidesRepository ridesRepo;
	
	public CabInvoiceMain()
	{
		ridesRepo = new RidesRepository();
	}

	public double calculateFare(double distance, int time,Subscription_Type type) 
	{
		double fare;
		if(type == Subscription_Type.NORMAL)
		{
			fare = COST_PER_KM * distance + COST_PER_MINUTE * time;
			return fare<MINIMUM_FARE ? MINIMUM_FARE : fare ;
		}
		else
		{
			fare = COST_PER_KM_PREMIMUM * distance + COST_PER_MINUTE_PREMIMUM * time;	
			return fare<MINIMUM_FARE_PREMIMUM ? MINIMUM_FARE_PREMIMUM : fare ;
		}
	}

	public double calculateTotalFare(Ride[] rides,Subscription_Type type) {
		double totalFare = 0;
		for(Ride ride : rides)
		{
			totalFare = totalFare + this.calculateFare(ride.distance, ride.time,type);
		}
		return totalFare;
	}

	public InvoiceSummary generateSummary(Ride[] rides,Subscription_Type type) {
		double totalFare = calculateTotalFare(rides,type);		
		return new InvoiceSummary(rides.length,totalFare);
	}

	public InvoiceSummary generateInvoice(int userId,Subscription_Type type) {
		Ride[] rides = (Ride[]) ridesRepo.getRideArray(userId);
		return generateSummary(rides,type);
	}

	public void addRidesToRepo(int userId, Ride[] ridesArray) {
		ridesRepo.addRidesToMap(userId,ridesArray);
	}
}
