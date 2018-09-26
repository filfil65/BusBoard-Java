package training.busboard;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bus {
	public String direction;
	public String line_name;
	public String best_departure_estimate;
	public String aimed_departure_time;
	public String tta; //time till expected arrival
	public Long longTime;  //Time in milliseconds between bus' arrival and 1st Jan 1970.
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	
	public Bus(String direction, String line_name, String best_departure_estimate, String aimed_departure_time, String reqTime) throws ParseException
	{
		this.direction = direction;
		this.line_name = line_name;
		this.best_departure_estimate = best_departure_estimate;
		this.aimed_departure_time = aimed_departure_time;
		
		Date date = format.parse(best_departure_estimate + ":00");
		this.longTime = date.getTime();
		
		Pattern pattern = Pattern.compile(".*([0-9][0-9]:[0-9][0-9]:[0-9][0-9])\\+([0-9][0-9]:[0-9][0-9])");
		Matcher matcher = pattern.matcher(reqTime);
		matcher.find();
		String formattedreqTime = matcher.group(1);
		String modifier = matcher.group(2);
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(best_departure_estimate + ":00");
		Date modifiedTime = format.parse(modifier + ":00");
//		String fuck = new SimpleDateFormat("HH:mm:ss").format(formattedreqTime);
		Date date2 = format.parse(formattedreqTime);
		long difference = date1.getTime() - date2.getTime() - modifiedTime.getTime();
		String output = (new SimpleDateFormat("HH:mm")).format(difference);
		this.tta = output;
		
	}
	
//	public void getTTA() throws ParseException
//	{		
//		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//		Date date1 = format.parse(best_departure_estimate + ":00");
//		String fuck = new SimpleDateFormat("HH:mm:ss").format(new Date());
//		Date date2 = format.parse(fuck);
//		long difference = date1.getTime() - date2.getTime();
//		String output = (new SimpleDateFormat("mm")).format(difference);
//		this.tta = output;
//	}
	
	public void getInfo()
	{
		System.out.println(aimed_departure_time + " No. " + line_name + " bus to " + direction + ".\n    Expected at " + best_departure_estimate);
	}
}
