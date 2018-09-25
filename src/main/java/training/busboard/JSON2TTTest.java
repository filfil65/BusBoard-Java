package training.busboard;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import com.google.gson.stream.JsonReader;

public class JSON2TTTest {
	public static void main(String args[]) throws IOException {
        JsonReader jsonReader = new JsonReader(new FileReader("jsonString.json"));

        jsonReader.beginObject();
        while(jsonReader.hasNext()){
        	String name = jsonReader.nextName();
        	System.out.println(name);
        }
        jsonReader.close();	
        
		
	}
}
