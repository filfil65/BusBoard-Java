package training.busboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws ParseException {
		// Sam's API ID is: c3b16dc7
		// Sam's API key is : 5293d7854df1ddc814d674a109060164

		Scanner readline = new Scanner(System.in);  
		System.out.println("Please enter a Post Code:");
		String input = readline.nextLine(); 
		readline.close();

		PCAPI postCodeApi = new PCAPI();
		String jsonPostCodeData = postCodeApi.PC2JSON(input);

		JSON2LL parserone = new JSON2LL(jsonPostCodeData);
		
		Place house = new Place();
		house.latitude = Double.toString(parserone.getLat());
		house.longitude = Double.toString(parserone.getLon());

		LLAPI llapi = new LLAPI();
		String jsonatcosdata = llapi.LL2JSON(house.latitude, house.longitude);

		JSON2ATCO jsonToAtcos =new JSON2ATCO(jsonatcosdata);
		ArrayList<String> atcos = jsonToAtcos.getAtco();
		
		ATCOAPI atcoapi = new ATCOAPI();
		for (int i=0; i<2 && i<atcos.size(); i++)
		{
			String jsonbusstopdata = atcoapi.atco2BusStop(atcos.get(i));
			
			JSON2BUSSTOP busStop = new JSON2BUSSTOP(jsonbusstopdata);
			
			ArrayList<Bus> buslist = busStop.getBusList();
			String ftext = "";
			if (i==1)
			{ ftext = "second";}
			
			System.out.println("\nThe " + ftext + " closest bus stop to you is: " + busStop.getStopName() + " " + busStop.getBearing() + ".\nThe next five buses to arrive there are:\n");

			for (int j=0; j<5 && j<buslist.size(); j++)
			{
				Bus bub = buslist.get(j);
				bub.getInfo();
				bub.getTTA();
			}
		}
//		Bus threethreeone = new Bus("Hertford", "331", "12:18","11:58");
//		BusStop stopOne = new BusStop();
//		stopOne.incomingBuses.add(threethreeone);
//		stopOne.incomingBuses.add(threethreeone);
//		ArrayList<Bus> buses =  stopOne.incomingBuses;
//		Bus bub = buses.get(0);
//		bub.getInfo();
//		bub.getTTA();
	}
}	
