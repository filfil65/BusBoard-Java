package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class LLAPI {
	
	public String LL2JSON(String lat, String lon)
	{
		Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		WebTarget webResource;
		webResource = client.target("http://transportapi.com/v3/uk/places.json?app_id=c3b16dc7&app_key=5293d7854df1ddc814d674a109060164&lat=" + lat + "&lon=" + lon + "&type=bus_stop");
		String response = webResource.request(MediaType.TEXT_PLAIN).get(String.class);
		return response;
	}

}
