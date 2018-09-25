package training.busboard;

public class Bus {
	String direction;
	String line_name;
	String best_departure_estimate;
	String aimed_departure_time;
	
	public void getInfo()
	{
		System.out.println("The " + aimed_departure_time + " No. " + line_name + " bus to " + direction + ".\n    Expected at " + best_departure_estimate);
	}
}
