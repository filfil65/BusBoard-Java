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
		
		ArrayList<BusStop> busStops = new ArrayList<BusStop>();
		ATCOAPI atcoapi = new ATCOAPI();
		for (int i=0; i<2 && i<atcos.size(); i++)
		{
			String jsonbusstopdata = atcoapi.atco2BusStop(atcos.get(i));
			JSON2BUSSTOP busStop = new JSON2BUSSTOP(jsonbusstopdata);
			//do some more jsonparsing here.
			//store in a hashmap?
//			BusStop outputfromtheparse = new BusStop();
//			busStops.add(outputfromtheparse);
			//System.out.println(jsonbusstopdata + "\n");
			System.out.println(busStop.getBusList().get(1).line_name);
			System.out.println(busStop.getStopName());
			System.out.println(busStop.getBearing());
		}
//
		Bus threethreeone = new Bus("Hertford", "331", "12:18","11:58");
		threethreeone.getInfo();
		
//		JSON2BUS newtest = new JSON2BUS();
//		newtest.Json2Bus("{\"atcocode\":\"210021310420\",\"smscode\":\"hrtdpdpj\",\"request_time\":\"2018-09-25T11:00:17+01:00\",\"name\":\"White Horse PH\",\"stop_name\":\"White Horse PH\",\"bearing\":\"SW\",\"indicator\":\"o/s\",\"locality\":\"High Cross\",\"location\":{\"type\":\"Point\",\"coordinates\":[-0.02259,51.84905]},\"departures\":{\"331\":[{\"mode\":\"bus\",\"line\":\"331\",\"line_name\":\"331\",\"direction\":\"Hertford\",\"operator\":\"ARHE\",\"operator_name\":\"Arriva (in Herts and Essex)\",\"date\":\"2018-09-25\",\"aimed_departure_time\":\"11:21\",\"expected_departure_date\":null,\"expected_departure_time\":null,\"best_departure_estimate\":\"11:21\",\"dir\":\"inbound\",\"id\":\"https://transportapi.com/v3/uk/bus/route/ARHE/331/inbound/210021310420/2018-09-25/11:21/timetable.json?app_id=c3b16dc7\\u0026app_key=5293d7854df1ddc814d674a109060164\",\"source\":\"Traveline timetable (not a nextbuses live region)\"},{\"mode\":\"bus\",\"line\":\"331\",\"line_name\":\"331\",\"direction\":\"Hertford\",\"operator\":\"ARHE\",\"operator_name\":\"Arriva (in Herts and Essex)\",\"date\":\"2018-09-25\",\"aimed_departure_time\":\"12:21\",\"expected_departure_date\":null,\"expected_departure_time\":null,\"best_departure_estimate\":\"12:21\",\"dir\":\"inbound\",\"id\":\"https://transportapi.com/v3/uk/bus/route/ARHE/331/inbound/210021310420/2018-09-25/12:21/timetable.json?app_id=c3b16dc7\\u0026app_key=5293d7854df1ddc814d674a109060164\",\"source\":\"Traveline timetable (not a nextbuses live region)\"}]}}");
//		Bus bub =newtest.buses[0];
//		bub.getInfo();
	}
}	
