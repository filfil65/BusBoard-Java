package training.busboard;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {
    	// Sam's API ID is: c3b16dc7
    	// Sam's API key is : 5293d7854df1ddc814d674a109060164
    	String apiid = "c3b16dc7";
    	String apikey = "5293d7854df1ddc814d674a109060164";
    	String atco = "0180BAA01330";
    	
    	Scanner readline = new Scanner(System.in);  
    	System.out.println("Please enter an ATCO code for a bus stop:");
		String input = readline.nextLine(); 
		readline.close();
    	
    	Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
//    	String schedule = client.target("https://transportapi.com/v3/uk/bus/stop/" + input +"/live.json?app_id=" + apiid + "&app_key=" + apikey + "&group=route&nextbuses=yes")
//    	        .request(MediaType.TEXT_PLAIN)
//    	        .get(String.class);
//    	System.out.println(schedule);
//    	String jsonResponse = schedule.readEntity(String.class);
    	
    	//
    	
		WebTarget webResource;
		webResource = client.target("https://transportapi.com/v3/uk/bus/stop/" + input + "/live.json?app_id=" + apiid + "&app_key=" + apikey + "&group=route&nextbuses=yes");
		String response = webResource.request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(response);
		//String jsonResponse = response.
    	
    }
}	
