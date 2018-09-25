package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class JSON2BUSSTOP {
	//Bus Stop
	String atcode;
	String stopName;
	//private String location;
	String bearing;
	//Bus
	String line;
	String direction;
	String departureTime;
	String eta; //Estimated time of arrival
	//Objects
	Bus bus;
	BusStop busStop;
	ArrayList<Bus> busList; 
	String reqTime;

	public JSON2BUSSTOP(String JSONString) throws ParseException {
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
	
	
}
