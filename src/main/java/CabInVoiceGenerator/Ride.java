package CabInVoiceGenerator;

public class Ride {
	
	public double distance;
	public int time;
	public CabRideEnum cabRideType ;
	
	public Ride(double distance, int time,CabRideEnum cabRideType) {
		this.distance = distance;
		this.time = time;
		this.cabRideType = cabRideType;
	}
	

}
