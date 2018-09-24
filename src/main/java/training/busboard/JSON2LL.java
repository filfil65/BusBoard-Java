package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class JSON2LL {
	
	String JSONString;
	String latitude;
	String longitude;
	ArrayList<String> out = new ArrayList<String>();
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
	        		if(name.equals("latitude")) {
	        			this.latitude = jsonReader.nextString();
	        		}
	        		if(name.equals("longitude")) {
	        			this.longitude = jsonReader.nextString();
	        		}
	        	}
	        	else {
	        		jsonReader.skipValue();
	        	}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		System.out.println("loop done");





		this.latitude = latitude;
		this.longitude = longitude;
		out.add(this.latitude);
		out.add(this.longitude);
		
	}

	public String getLat() {
		return this.out.get(1);
	}
	
}
