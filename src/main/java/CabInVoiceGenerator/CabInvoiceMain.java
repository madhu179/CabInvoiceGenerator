package CabInVoiceGenerator;

public class CabInvoiceMain {
	private double COST_PER_KM = 10.0;
	private int COST_PER_MINUTE = 1;

	public double calculateFare(double distance, int time) 
	{
		return COST_PER_KM * distance + COST_PER_MINUTE * time;
	}
}
