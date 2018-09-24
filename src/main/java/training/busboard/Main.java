package training.busboard;

import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {
    	// Sam's API ID is: c3b16dc7
    	// Sam's API key is : 5293d7854df1ddc814d674a109060164
    	
    	Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		WebTarget webResource;
		webResource = client.target("http://transportapi.com/v3/uk/places.json?app_id=c3b16dc7&app_key=5293d7854df1ddc814d674a109060164&lat=" + "51.389578" + "&lon=" + "-2.339649" + "&type=bus_stop");
		String response = webResource.request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(response);
    	
    	Scanner readline = new Scanner(System.in);  
    	System.out.println("Please enter a Post Code:");
		String input = readline.nextLine(); 
		readline.close();
		
		PCAPI pcapi = new PCAPI();
		String jsonpostcodedata = pcapi.PC2JSON(input);
		System.out.println(jsonpostcodedata);
		
		//Parse jsonpostcodedata to output String lat and String lon
		String lat = "";
		String lon = "";
		//Parse jsonpostcodedata to output String lat and String lon
		
		LLAPI llapi = new LLAPI();
		String jsonatcosdata = llapi.LL2JSON(lat, lon);
		System.out.println(jsonatcosdata);
		
		//Parse jsonatcosdata to output a list of ATCO codes (as a ArrayList<String>).
		ArrayList<String> atcos = new ArrayList<String>();
		//Parse jsonatcosdata to output a list of ATCO codes (as a ArrayList<String>).
		
		ArrayList<BusStop> busStops = new ArrayList<BusStop>();
		ATCOAPI atcoapi = new ATCOAPI();
    	for (int i=0; i<2; i++)
    	{
    		String jsonbusstopdata = atcoapi.atco2BusStop(atcos.get(i));
    		//do some more jsonparsing here.
    		//store in a hashmap?
    		BusStop outputfromtheparse = new BusStop();
    		busStops.add(outputfromtheparse);
    	}
    	
    }
}	
