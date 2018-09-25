package training.busboard;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		// Sam's API ID is: c3b16dc7
		// Sam's API key is : 5293d7854df1ddc814d674a109060164

		Scanner readline = new Scanner(System.in);  
		System.out.println("Please enter a Post Code:");
		String input = readline.nextLine(); 
		readline.close();

		PCAPI pcapi = new PCAPI();
		String jsonpostcodedata = pcapi.PC2JSON(input);
		System.out.println(jsonpostcodedata);

		JSON2LL parserone = new JSON2LL(jsonpostcodedata);
		System.out.println(parserone);
		
		Place house = new Place();
		house.latitude = Double.toString(parserone.getLat());
		house.longitude = Double.toString(parserone.getLon());
		System.out.println("Lat is: " + house.latitude);
		System.out.println("Long is: " + house.longitude);

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
