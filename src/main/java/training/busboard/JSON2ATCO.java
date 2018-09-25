package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class JSON2ATCO {
	ArrayList<String> atcoList;
	String JSONString;

	public  JSON2ATCO (String JSONString){
		this.JSONString = JSONString;
		// Read JSON - IN THE CONSTRUCTOR
		JsonReader jsonReader = new JsonReader(new StringReader(JSONString));
		try {
			jsonReader.beginObject();
			String name = jsonReader.nextName();
			if(name.equals("member")) {
				jsonReader.beginArray();
				jsonReader.beginObject();
				String name2 = jsonReader.nextName();
				if(name.equals("atcocode")) {
					atcoList.add(jsonReader.nextString());
				}
				else {
					jsonReader.skipValue();
				}
			}
			else {
				jsonReader.skipValue();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArrayList<String> getAtco() {
		return this.atcoList;
	}

}
