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

		PCAPI postCodeApi = new PCAPI();
		String jsonPostCodeData = postCodeApi.PC2JSON(input);
//		System.out.println(jsonpostcodedata);

		JSON2LL parserone = new JSON2LL(jsonPostCodeData);
//		System.out.println(parserone);
		
		Place house = new Place();
		house.latitude = Double.toString(parserone.getLat());
		house.longitude = Double.toString(parserone.getLon());
//		System.out.println("Lat is: " + house.latitude);
//		System.out.println("Long is: " + house.longitude);

		LLAPI llapi = new LLAPI();
		String jsonatcosdata = llapi.LL2JSON(house.latitude, house.longitude);
		System.out.println(jsonatcosdata);

		//Parse jsonatcosdata to output a list of two ATCO codes (as a ArrayList<String>).
		JSON2ATCO jsonToAtcos =new JSON2ATCO(jsonatcosdata);
		ArrayList<String> atcos = jsonToAtcos.getAtco();
		//Parse jsonatcosdata to output a list of two ATCO codes (as a ArrayList<String>).

		System.out.println(atcos);
		
//		ArrayList<BusStop> busStops = new ArrayList<BusStop>();
//		ATCOAPI atcoapi = new ATCOAPI();
//		for (int i=0; i<2 && i<atcos.size(); i++)
//		{
//			String jsonbusstopdata = atcoapi.atco2BusStop(atcos.get(i));
//			//do some more jsonparsing here.
//			//store in a hashmap?
//			BusStop outputfromtheparse = new BusStop();
//			busStops.add(outputfromtheparse);
//		}
////
//		Bus threethreeone = new Bus();
//		threethreeone.aimed_departure_time = "11.55";
//		threethreeone.best_departure_estimate = "12:08";
//		threethreeone.direction = "Hertford";
//		threethreeone.line_name = "331";
//		
//		threethreeone.getInfo();
	}
}	
