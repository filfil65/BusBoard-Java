package training.busboard;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class JSON2ATCO {
	String atco;
	ArrayList<String> atcoList;
	String JSONString;

	public  JSON2ATCO (String JSONString){
		this.atcoList = new ArrayList<String>();
		this.JSONString = JSONString;
		// Read JSON - IN THE CONSTRUCTOR
		JsonReader jsonReader = new JsonReader(new StringReader(JSONString));
		try {
			jsonReader.beginObject();
			while(jsonReader.hasNext()) {
				String name = jsonReader.nextName();
				if (name.equals("member")) {
					jsonReader.beginArray();
					while(jsonReader.hasNext()){
						jsonReader.beginObject();
						while(jsonReader.hasNext()) {
							String name2 = jsonReader.nextName();
							if(name2.equals("atcocode")) {
								this.atco = jsonReader.nextString();
								this.atcoList.add(this.atco);
							}
							else {
								jsonReader.skipValue();
							}
						}
						jsonReader.endObject();
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
	}


	public ArrayList<String> getAtco() {
		return this.atcoList;
	}

}
