package CabInVoiceGenerator;

public enum CabRideEnum {
	NORMAL(10.0,1,5.0),PREMIMUM(15.0,2,20.0);
	
	private double costPerSq;
	private int costPerMinute;
	private double minimumFare;
	
	CabRideEnum(double costPerSq, int costPerMinute, double minimumFare) {
		this.costPerSq = costPerSq;
		this.costPerMinute = costPerMinute;
		this.minimumFare = minimumFare;
	}
	
	public double calculateFareForRide(Ride ride)
	{
		double fare=ride.distance*costPerSq+ride.time*costPerMinute;
		return fare<minimumFare ? minimumFare : fare;
	}
	


}
