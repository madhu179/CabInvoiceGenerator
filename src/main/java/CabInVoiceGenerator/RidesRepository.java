package CabInVoiceGenerator;

import java.util.*;

public class RidesRepository {
	
	HashMap<Integer,Ride[]> ridesMap;
	
	public RidesRepository()
	{
		ridesMap = new HashMap<Integer,Ride[]>();
	}
	
	public void addRidesToMap(int[] userArray, Ride[][] ridesArray)
	{
		int counter=0;
		for(int i : userArray)
		{
			if(ridesMap.containsKey(i))
			{
				ridesMap.replace(i, ridesArray[counter]);
			}
			else
			{
				ridesMap.put(i,ridesArray[counter]);
			}
			counter++;
		}
	}
	
	public Ride[] getRideArray(int userId)
	{
		if(ridesMap.containsKey(userId))
		{
			return ridesMap.get(userId);
		}
		return null;
	}

	

}
