package CabInVoiceGenerator;

public class InvoiceSummary {
	public int totalRides = 0;
	public double totalFare = 0.0;
	public double avgFare = 0.0;

	public InvoiceSummary(int totalRides, double totalFare) {
		this.totalFare = totalFare;
		this.totalRides = totalRides;
		this.avgFare = totalFare/totalRides;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceSummary other = (InvoiceSummary) obj;
		if (Double.doubleToLongBits(avgFare) != Double.doubleToLongBits(other.avgFare))
			return false;
		if (Double.doubleToLongBits(totalFare) != Double.doubleToLongBits(other.totalFare))
			return false;
		if (totalRides != other.totalRides)
			return false;
		return true;
	}
	
	

}
