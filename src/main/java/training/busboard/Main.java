package training.busboard;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {
	public static ArrayList<BusStop> main(String postcode) throws ParseException {
		// Sam's API ID is: c3b16dc7
		// Sam's API key is : 5293d7854df1ddc814d674a109060164

		PCAPI postCodeApi = new PCAPI();
		String jsonPostCodeData = postCodeApi.PC2JSON(postcode);

		JSON2LL parserone = new JSON2LL(jsonPostCodeData);

		Place house = new Place();
		house.latitude = Double.toString(parserone.getLat());
		house.longitude = Double.toString(parserone.getLon());

		LLAPI llapi = new LLAPI();
		String jsonatcosdata = llapi.LL2JSON(house.latitude, house.longitude);

		JSON2ATCO jsonToAtcos =new JSON2ATCO(jsonatcosdata);
		ArrayList<String> atcos = jsonToAtcos.getAtco();

		ATCOAPI atcoapi = new ATCOAPI();

		//Create Bus Stop Objects
		BusStop busStop1 = new BusStop(atcoapi.atco2BusStop(atcos.get(0)));
		BusStop busStop2 = new BusStop(atcoapi.atco2BusStop(atcos.get(1)));

		ArrayList<BusStop> out = new ArrayList<BusStop>();
		out.add(busStop1);
		out.add(busStop2);
		return out;
	}
}

