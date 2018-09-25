package training.busboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

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
			BusStop busStop = new BusStop(jsonbusstopdata);

			HashMap<Long, Bus> busList = new HashMap<Long,Bus>();
			for(Bus eachBus : busStop.getBusList()) {
				busList.put(eachBus.longTime, eachBus);
			}
			SortedSet<Long> keys = new TreeSet<Long>(busList.keySet());
			List<Long> list = new ArrayList<Long>(keys.size());
			list.addAll(keys);

			ArrayList<Bus> fiveBuses = new ArrayList<Bus>();
			for(int k=0; k<5 && k<keys.size(); k++) {
				fiveBuses.add(k,busList.get(list.get(k)));
			}	
			//remove below
//			String ftext = "";
//			if (i==1)
//			{ ftext = "second";}
//			System.out.println("\nThe " + ftext + " closest bus stop to you is: " + busStop.getStopName() + " " + busStop.getBearing() + ".\nThe next five buses to arrive there are:\n");
//
//			for (int j=0; j<5 && j<fiveBuses.size(); j++)
//			{
//				Bus bub = fiveBuses.get(j);
//				bub.getInfo();
//				bub.getTTA();
			}
		}
	}
}	
