package training.busboard;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bus {
	String direction;
	String line_name;
	String best_departure_estimate;
	String aimed_departure_time;
	Long longTime;  //Time in milliseconds between bus' arrival and 1st Jan 1970.
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	
	public Bus(String direction, String line_name, String best_departure_estimate, String aimed_departure_time) throws ParseException
	{
		this.direction = direction;
		this.line_name = line_name;
		this.best_departure_estimate = best_departure_estimate;
		this.aimed_departure_time = aimed_departure_time;
		Date date1 = format.parse(best_departure_estimate + ":00");
		this.longTime = date1.getTime();
		
	}
	
	public void getTTA() throws ParseException
	{		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(best_departure_estimate + ":00");
		String fuck = new SimpleDateFormat("HH:mm:ss").format(new Date());
		Date date2 = format.parse(fuck);
		long difference = date1.getTime() - date2.getTime();
		String output = (new SimpleDateFormat("mm")).format(difference);
		System.out.println("Will arrive in " + output + " minute(s).\n");
	}
	
	public void getInfo()
	{
		System.out.println(aimed_departure_time + " No. " + line_name + " bus to " + direction + ".\n    Expected at " + best_departure_estimate);
	}
}
