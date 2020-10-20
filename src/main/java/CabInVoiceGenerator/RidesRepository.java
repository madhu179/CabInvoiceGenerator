package CabInVoiceGenerator;

import java.util.*;

public class RidesRepository {

	HashMap<Integer, ArrayList<Ride>> ridesMap;

	public RidesRepository() {
		ridesMap = new HashMap<Integer, ArrayList<Ride>>();
	}

	public void addRidesToMap(int userId, Ride[] ridesArray) {
		if (ridesMap.containsKey(userId)) {
			ArrayList<Ride> ride = ridesMap.get(userId);
			if (ride != null) {
				ride.addAll(Arrays.asList(ridesArray));
				ridesMap.replace(userId, ride);
			} else {
				ride = (ArrayList<Ride>) Arrays.asList(ridesArray);
				ridesMap.replace(userId, ride);
			}
		} else {
			ArrayList<Ride> ride =  new ArrayList<Ride>(Arrays.asList(ridesArray));
			ridesMap.put(userId, ride);
		}
	}

	public Ride[] getRideArray(int userId) {
		if (ridesMap.containsKey(userId)) {
			ArrayList<Ride> rides = ridesMap.get(userId);
			Ride[] ridesarray= new Ride[rides.size()];
			return rides.toArray(ridesarray);
		}
		return null;
	}

}
