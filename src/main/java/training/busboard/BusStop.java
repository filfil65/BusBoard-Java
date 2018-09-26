package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gson.stream.JsonReader;

public class BusStop {
	//Bus Stop
	String atcode;
	public String stopName;
	//private String location;
	String bearing;
	//Bus
	String line;
	String direction;
	String departureTime;
	String eta; //Estimated time of arrival
	//Objects
	Bus bus;
	ArrayList<Bus> busList; 
	String reqTime;

	public BusStop(String JSONString) throws ParseException {
		this.busList = new ArrayList<Bus>();
		// Read JSON - IN THE CONSTRUCTOR
		JsonReader jsonReader = new JsonReader(new StringReader(JSONString));
		try {
			jsonReader.beginObject();
			while(jsonReader.hasNext()) {
				String name = jsonReader.nextName();
				if(name.equals("atcocode")) {
					this.atcode = jsonReader.nextString();
				}
				else if(name.equals("request_time")){
					this.reqTime = jsonReader.nextString();
				}
				else if(name.equals("stop_name")){
					this.stopName = jsonReader.nextString();
				}
				else if(name.equals("bearing")){
					this.bearing = jsonReader.nextString();
				}
				else if(name.equals("departures")) {
					jsonReader.beginObject();
					while(jsonReader.hasNext()) {
						jsonReader.skipValue();
						jsonReader.beginArray();
						while(jsonReader.hasNext()) {
							jsonReader.beginObject();
							while(jsonReader.hasNext()) {
								name = jsonReader.nextName();

								if(name.equals("line_name")) {
									this.line=jsonReader.nextString();
								}
								else if(name.equals("direction")) {
									this.direction=jsonReader.nextString();
								}
								else if(name.equals("aimed_departure_time")) {
									this.departureTime=jsonReader.nextString();
								}
								else if(name.equals("best_departure_estimate")) {
									this.eta=jsonReader.nextString();
								}
								else {
									jsonReader.skipValue();
								}
							}
							this.bus = new Bus(this.direction, this.line, this.eta, this.departureTime);
							this.busList.add(this.bus);
							jsonReader.endObject();
						}
						jsonReader.endArray();
					}
					jsonReader.endObject();
				}
				else if(name.equals("source")) {
					break;
				}
				else {
					jsonReader.skipValue();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Bus> getBusList() {
		return this.busList;
	}
	public String getStopName() {
		return this.stopName;
	}
	public String getBearing() {
		return this.bearing;
	}
	public String getReqTime() {
		return this.reqTime;
	}
	public ArrayList<Bus> getNextFive() {
		
		HashMap<Long, Bus> busList = new HashMap<Long,Bus>();
		for(Bus eachBus : this.busList) {
			busList.put(eachBus.longTime, eachBus);
		}
		SortedSet<Long> keys = new TreeSet<Long>(busList.keySet());
		List<Long> list = new ArrayList<Long>(keys.size());
		list.addAll(keys);

		ArrayList<Bus> fiveBuses = new ArrayList<Bus>();
		for(int k=0; k<5 && k<keys.size(); k++) {
			fiveBuses.add(k,busList.get(list.get(k)));
		}	
		return fiveBuses;
	}
	
}
