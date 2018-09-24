package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class ATCOAPI {
	
	String apiid = "c3b16dc7";
	String apikey = "5293d7854df1ddc814d674a109060164";
	
	public String atco2BusStop(String atco) {
		
		Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		WebTarget webResource;
		webResource = client.target("https://transportapi.com/v3/uk/bus/stop/" + atco + "/live.json?app_id=" + apiid + "&app_key=" + apikey + "&group=route&nextbuses=yes");
		String response = webResource.request(MediaType.TEXT_PLAIN).get(String.class);
		return response;
	}

}
