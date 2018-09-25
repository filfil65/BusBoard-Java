package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class JSON2LL {
	
	String JSONString;
	Double latitude;
	Double longitude;
	ArrayList<Double> out = new ArrayList<Double>();
	// ~Constructor
	public JSON2LL (String JSONString) {

		this.JSONString = JSONString;
		// Read JSON - IN THE CONSTRUCTOR
		JsonReader jsonReader = new JsonReader(new StringReader(JSONString));
        try {
			jsonReader.beginObject();
			while(jsonReader.hasNext()){
	        	String name = jsonReader.nextName(); 
	        	if(name.equals("result")) {
	        		jsonReader.beginObject();
	        		while(jsonReader.hasNext()) {
	        			String name2 = jsonReader.nextName();
	        			if(name2.equals("latitude")) {
	        				//this.latitude = jsonReader.nextString();
	        				this.latitude = jsonReader.nextDouble();
	        				Double latt = this.latitude;
	        			}
	        			else if(name2.equals("longitude")) {
	        				//this.longitude = jsonReader.nextString();
	        				this.longitude = jsonReader.nextDouble();
	        				Double longg = this.longitude;
	        			}
	        			else {
	        				jsonReader.skipValue();
	        			}
	        		}
	        	}
	        	else {
	        		jsonReader.skipValue();
	        	}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.add(0,this.latitude);
		out.add(1,this.longitude);		
	}

	public Double getLat() {
		return this.out.get(0);
	}
	
	public Double getLon() {
		return this.out.get(1);
	}
	
}
